package com.example.farshbazar.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.farshbazar.ui.viewmodel.MainViewModel

@Composable
fun AboutScreen(
    onNavigateToManifesto: () -> Unit,
    onNavigateToCollaboration: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        Text(
            text = "About Farsh Bazaar",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "درباره فرش بازار - پاسداشت هنر و کرامت قالی‌بافان",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        // Mission Statement Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Preserving 2,500 Years of Persian Carpet Legacy",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Farsh Bazaar was established to connect authentic master carpet weavers, rural artisan cooperatives, and independent vendors directly with collectors worldwide.",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        // 4 Pillars Grid
        Text(
            text = "Our Four Core Pillars",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        val pillars = listOf(
            Triple(Icons.Default.VerifiedUser, "Guild Credibility", "Authenticity verification for every hand-knotted carpet and weaver profile."),
            Triple(Icons.Default.AttachMoney, "Sustainable Income", "Restoring fair compensation and eliminating predatory middleman commissions."),
            Triple(Icons.Default.Public, "Global Connection", "Direct virtual showrooms accessible across continents without geographical barriers."),
            Triple(Icons.Default.Favorite, "Heritage Preservation", "Preserving traditional vegetable dyes, silk spinning, and rare tribal knotting techniques.")
        )

        pillars.forEach { (icon, title, desc) ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = desc,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        // Tribute Section
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "A Heartfelt Tribute | ادای احترام",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
                Text(
                    text = "This platform is dedicated in loving tribute to our elders, fathers, and master artisans whose hands shaped the looms of history. May their spirit and wisdom guide every thread woven.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = 0.9f)
                )
            }
        }

        // Navigation Action Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = onNavigateToManifesto,
                modifier = Modifier.weight(1f)
            ) {
                Icon(Icons.Default.MenuBook, contentDescription = null)
                Spacer(modifier = Modifier.width(4.dp))
                Text("Our Vision")
            }
            OutlinedButton(
                onClick = onNavigateToCollaboration,
                modifier = Modifier.weight(1f)
            ) {
                Icon(Icons.Default.Handshake, contentDescription = null)
                Spacer(modifier = Modifier.width(4.dp))
                Text("Partnership")
            }
        }
    }
}
