package com.example.farshbazar.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.farshbazar.ui.viewmodel.MainViewModel

val multilingualPerspectives = listOf(
    Triple("Persian (فارسی)", "فرش دستباف، شناسنامه فرهنگی و صدای پنهان هنرمندان بی‌ادعای ایران است.", "Farsh Bazaar is the voice of Iranian carpet artisans."),
    Triple("Arabic (العربية)", "السجاد الإيراني اليدوي هو تحفة فنية تجمع بين الأصالة والجمال العريق.", "Persian carpets combine timeless beauty and heritage."),
    Triple("Azerbaijani (Azərbaycan)", "İran əl xalçası əsrlər boyu yaşatılan incəsənət və mədəniyyət xəzinəsidir.", "Handwoven carpet is a cultural treasure preserved through centuries."),
    Triple("Chinese (中文)", "波斯手织地毯是传世的的艺术瑰宝，将地毯与全球文化爱好者相连。", "Persian rugs are artistic gems connecting cultures globally."),
    Triple("Hindi (हिंदी)", "फारसी कालीन केवल कला नहीं है, यह सदियों पुरानी परंपरा का जीवंत प्रतीक है।", "Persian rug is a living symbol of centuries-old tradition."),
    Triple("Japanese (日本語)", "手織りのペルシャ絨毯は、職人の魂と伝統が織りなす素晴らしい芸術品です。", "Handwoven rugs embody the artisan's soul and legacy."),
    Triple("Russian (Русский)", "Иранский ковер ручной работы — это шедевр искусства, объединяющий истории веков.", "Persian handwoven rug is a masterpiece connecting centuries."),
    Triple("Spanish (Español)", "Las alfombras persas hechas a mano son tesoros artísticos llenos de historia y elegancia.", "Handmade Persian rugs are artistic treasures filled with elegance."),
    Triple("Turkish (Türkçe)", "İran el dokuması halıları, geleneksel ustalığın ve zarafetin dünyadaki en nadide simgesidir.", "Persian rugs are premier symbols of craftsmanship."),
    Triple("Urdu (اردو)", "ایران کا دستکاری قالین صرف فن نہیں بلکہ ایک عظیم ثقافتی میراث کا باضابطہ ترجمان ہے۔", "Persian rug is an official ambassador of cultural heritage.")
)

@Composable
fun FeedbackScreen(viewModel: MainViewModel) {
    val scrollState = rememberScrollState()

    var feedbackText by remember { mutableStateOf("") }
    var selectedRole by remember { mutableStateOf("buyer") } // 'buyer' or 'vendor'
    var isSubmitted by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Community Feedback & Suggestions",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "بازخوردها و نظرات جامعه کاربران و قالی‌بافان",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        // Submit Form Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Submit Your Idea or Feedback",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text("I am a:", fontWeight = FontWeight.Medium)
                    FilterChip(
                        selected = selectedRole == "buyer",
                        onClick = { selectedRole = "buyer" },
                        label = { Text("Collector / Buyer") }
                    )
                    FilterChip(
                        selected = selectedRole == "vendor",
                        onClick = { selectedRole = "vendor" },
                        label = { Text("Weaver / Vendor") }
                    )
                }

                OutlinedTextField(
                    value = feedbackText,
                    onValueChange = {
                        feedbackText = it
                        isSubmitted = false
                    },
                    label = { Text("Your Suggestion / Message") },
                    placeholder = { Text("Share feature requests, weaver support ideas, or platform improvements...") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3
                )

                if (isSubmitted) {
                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Thank you! Your suggestion has been recorded in the ecosystem hub.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }

                Button(
                    onClick = {
                        if (feedbackText.isNotBlank()) {
                            viewModel.submitSuggestion(feedbackText, selectedRole) {
                                isSubmitted = true
                                feedbackText = ""
                            }
                        }
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Icon(Icons.Default.Send, contentDescription = null)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Submit Suggestion")
                }
            }
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        // AI Multilingual Perspectives Section
        Text(
            text = "AI Multilingual Global Perspectives",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Cultural reflections on Persian carpet heritage translated in 10 global languages.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        multilingualPerspectives.forEach { (lang, text, translation) ->
            var expanded by remember { mutableStateOf(false) }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded },
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = lang,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Icon(
                            imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                            contentDescription = "Toggle"
                        )
                    }

                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                    AnimatedVisibility(visible = expanded) {
                        Text(
                            text = "English: $translation",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
        }
    }
}
