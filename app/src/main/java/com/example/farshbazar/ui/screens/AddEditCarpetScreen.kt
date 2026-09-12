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
import com.example.farshbazar.data.model.Carpet
import com.example.farshbazar.ui.viewmodel.MainViewModel

@Composable
fun AddEditCarpetScreen(
    carpetId: String?,
    vendorId: String,
    viewModel: MainViewModel,
    onBack: () -> Unit
) {
    val carpets by viewModel.carpets.collectAsState()
    val existingCarpet = if (carpetId != null) carpets.find { it.id == carpetId } else null

    var name by remember { mutableStateOf(existingCarpet?.name ?: "") }
    var price by remember { mutableStateOf(existingCarpet?.price ?: "") }
    var imageUrl by remember { mutableStateOf(existingCarpet?.imageUrl ?: "") }
    var description by remember { mutableStateOf(existingCarpet?.description ?: "") }
    var consignment by remember { mutableStateOf(existingCarpet?.consignment ?: false) }

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
                text = if (existingCarpet != null) "Edit Carpet" else "Add New Carpet",
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
                    label = { Text("Carpet Title / Name *") },
                    placeholder = { Text("e.g. Royal Silk Isfahan") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text("Price (USD / Currency) *") },
                    placeholder = { Text("e.g. $2,450") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = imageUrl,
                    onValueChange = { imageUrl = it },
                    label = { Text("Image URL") },
                    placeholder = { Text("https://images.unsplash.com/...") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description & Weaving Details *") },
                    placeholder = { Text("Describe knot density, silk/wool material, origin, and design patterns...") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 4
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = consignment,
                        onCheckedChange = { consignment = it }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Mark as Catalog Consignment Item",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        if (name.isBlank() || price.isBlank() || description.isBlank()) {
                            errorMessage = "Please fill in all required fields marked with *."
                        } else {
                            if (existingCarpet != null) {
                                viewModel.updateCarpet(
                                    existingCarpet.copy(
                                        name = name,
                                        price = price,
                                        imageUrl = if (imageUrl.isBlank()) existingCarpet.imageUrl else imageUrl,
                                        description = description,
                                        consignment = consignment
                                    ),
                                    onComplete = onBack
                                )
                            } else {
                                viewModel.addCarpet(
                                    name = name,
                                    price = price,
                                    imageUrl = imageUrl,
                                    description = description,
                                    vendorId = vendorId,
                                    consignment = consignment,
                                    onComplete = onBack
                                )
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (existingCarpet != null) "Save Changes" else "Add Carpet to Showroom")
                }
            }
        }
    }
}
