package com.example.farshbazar.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.farshbazar.ui.viewmodel.MainViewModel

@Composable
fun AddEditVendorScreen(
    vendorId: String?,
    viewModel: MainViewModel,
    onNavigateToVendorDetail: (String) -> Unit,
    onBack: () -> Unit
) {
    val vendors by viewModel.vendors.collectAsState()
    val existingVendor = if (vendorId != null) vendors.find { it.id == vendorId } else null
    val currentUser by viewModel.currentUser.collectAsState()

    var name by remember { mutableStateOf(existingVendor?.name ?: "") }
    var location by remember { mutableStateOf(existingVendor?.location ?: "") }
    var specialties by remember { mutableStateOf(existingVendor?.specialties ?: "") }
    var bio by remember { mutableStateOf(existingVendor?.bio ?: "") }

    var errorMessage by remember { mutableStateOf<String?>(null) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = if (existingVendor != null) "Edit Showroom Profile" else "Become a Vendor / Artisan",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Set up your virtual showroom to display carpet collections to collectors and buyers worldwide.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                if (errorMessage != null) {
                    Text(
                        text = errorMessage!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Vendor / Showroom Name *") },
                    placeholder = { Text("e.g. Tabriz Master Weavers Guild") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = location,
                    onValueChange = { location = it },
                    label = { Text("Location / Origin *") },
                    placeholder = { Text("e.g. Tabriz, Iran") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = specialties,
                    onValueChange = { specialties = it },
                    label = { Text("Specialties (Comma Separated) *") },
                    placeholder = { Text("e.g. Fine Silk, Classic Medallion, Nomadic Gabbeh") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = bio,
                    onValueChange = { bio = it },
                    label = { Text("About / Bio *") },
                    placeholder = { Text("Describe your workshop, lineage of craftsmanship, or heritage...") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 4
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        if (name.isBlank() || location.isBlank() || specialties.isBlank() || bio.isBlank()) {
                            errorMessage = "Please complete all fields marked with *."
                        } else {
                            if (existingVendor != null) {
                                viewModel.updateVendorProfile(
                                    existingVendor.copy(
                                        name = name,
                                        location = location,
                                        specialties = specialties,
                                        bio = bio
                                    ),
                                    onComplete = { onNavigateToVendorDetail(existingVendor.id) }
                                )
                            } else {
                                viewModel.createVendorProfile(
                                    name = name,
                                    location = location,
                                    specialties = specialties,
                                    bio = bio,
                                    userId = currentUser?.id ?: "user-1",
                                    onComplete = { createdId -> onNavigateToVendorDetail(createdId) }
                                )
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (existingVendor != null) "Update Showroom" else "Create Showroom")
                }
            }
        }
    }
}
