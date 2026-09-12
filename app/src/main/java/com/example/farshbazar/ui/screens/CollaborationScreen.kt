package com.example.farshbazar.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CollaborationScreen() {
    val scrollState = rememberScrollState()
    var showContactDialog by remember { mutableStateOf(false) }
    var selectedPillar by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Partnership & Collaboration",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "مشارکت و هم‌آفرینی در اکوسیستم فرش بازار",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        val pillars = listOf(
            Triple(
                "Invest in Vision | سرمایه‌گذاری",
                "Support master weaver guilds, expand ethical digital infrastructure, and fund artisan support reserves.",
                Icons.Default.TrendingUp
            ),
            Triple(
                "Amplify Story | رسانه و بازاریابی",
                "Help tell the story of Persian carpet heritage to global interior designers, galleries, and culture centers.",
                Icons.Default.Campaign
            ),
            Triple(
                "Lend Talent | همکاری تخصصی",
                "Contribute engineering, design, translation, or carpet valuation expertise to the FB New Meta platform.",
                Icons.Default.Code
            )
        )

        pillars.forEach { (title, desc, icon) ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Text(
                        text = desc,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Button(
                        onClick = {
                            selectedPillar = title
                            showContactDialog = true
                        },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Connect / پیام همکاری")
                    }
                }
            }
        }
    }

    if (showContactDialog) {
        AlertDialog(
            onDismissRequest = { showContactDialog = false },
            title = { Text("Partnership Interest") },
            text = {
                Text("Thank you for your interest in '$selectedPillar'. Please reach out directly to siavashhamiri@gmail.com or join our Telegram ecosystem channel.")
            },
            confirmButton = {
                Button(onClick = { showContactDialog = false }) {
                    Text("Got It")
                }
            }
        )
    }
}
