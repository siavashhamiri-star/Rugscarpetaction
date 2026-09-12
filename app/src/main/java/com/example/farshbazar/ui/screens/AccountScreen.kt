package com.example.farshbazar.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.farshbazar.ui.viewmodel.MainViewModel

@Composable
fun AccountScreen(
    viewModel: MainViewModel,
    onNavigateToVendorDetail: (String) -> Unit,
    onNavigateToBecomeVendor: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onNavigateToFeedback: () -> Unit
) {
    val currentUser by viewModel.currentUser.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "User Account & Profile",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        // User Details Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.size(80.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = currentUser?.displayName ?: "Siavash Hamiri",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = currentUser?.email ?: "siavashhamiri@gmail.com",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (currentUser?.isVendor == true && currentUser?.vendorId != null) {
                    AssistChip(
                        onClick = { onNavigateToVendorDetail(currentUser!!.vendorId!!) },
                        label = { Text("Verified Artisan Vendor") },
                        leadingIcon = { Icon(Icons.Default.Verified, contentDescription = null, tint = MaterialTheme.colorScheme.primary) }
                    )
                } else {
                    OutlinedButton(onClick = onNavigateToBecomeVendor) {
                        Icon(Icons.Default.Storefront, contentDescription = null)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Become a Vendor")
                    }
                }
            }
        }

        // Account Quick Actions
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                if (currentUser?.isVendor == true && currentUser?.vendorId != null) {
                    ListItem(
                        headlineContent = { Text("My Virtual Showroom") },
                        supportingContent = { Text("Manage carpets, reviews, and showroom details") },
                        leadingContent = { Icon(Icons.Default.Store, contentDescription = null) },
                        modifier = Modifier.fillMaxWidth(),
                        trailingContent = {
                            Button(onClick = { onNavigateToVendorDetail(currentUser!!.vendorId!!) }) {
                                Text("Open")
                            }
                        }
                    )
                    Divider()
                }

                ListItem(
                    headlineContent = { Text("About & Guild Heritage") },
                    supportingContent = { Text("Pillars, tribute to elders, and mission statement") },
                    leadingContent = { Icon(Icons.Default.Info, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth(),
                    trailingContent = {
                        IconButton(onClick = onNavigateToAbout) {
                            Icon(Icons.Default.ChevronRight, contentDescription = null)
                        }
                    }
                )

                Divider()

                ListItem(
                    headlineContent = { Text("Community Feedback") },
                    supportingContent = { Text("Submit ideas & view AI multilingual perspectives") },
                    leadingContent = { Icon(Icons.Default.Comment, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth(),
                    trailingContent = {
                        IconButton(onClick = onNavigateToFeedback) {
                            Icon(Icons.Default.ChevronRight, contentDescription = null)
                        }
                    }
                )
            }
        }

        // Session Toggle
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = if (currentUser?.isLoggedIn == true) "Logged In" else "Logged Out",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "FB New Meta Single Sign-On Active",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                OutlinedButton(onClick = { viewModel.toggleAuth() }) {
                    Icon(
                        imageVector = if (currentUser?.isLoggedIn == true) Icons.Default.Logout else Icons.Default.Login,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(if (currentUser?.isLoggedIn == true) "Log Out" else "Log In")
                }
            }
        }
    }
}
