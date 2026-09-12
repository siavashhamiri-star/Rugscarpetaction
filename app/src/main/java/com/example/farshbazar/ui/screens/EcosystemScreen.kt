package com.example.farshbazar.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.farshbazar.data.model.EcosystemApp

val ecosystemApps = listOf(
    EcosystemApp(
        name = "Farsh Bazaar",
        faName = "فرش بازار",
        description = "Global handwoven carpet marketplace connecting master artisans directly with collectors.",
        faDescription = "بازار جهانی فرش دستباف اتصال مستقیم استادکاران با خریداران.",
        status = "Live"
    ),
    EcosystemApp(
        name = "LingoView (Project Legwit)",
        faName = "گویا (پروژه لگویت)",
        description = "Language learning revolution converting theoretical knowledge directly into fluent communication capability.",
        faDescription = "انقلاب آموزش زبان تبدیل دانش نظری به توانمندی گویندگی.",
        status = "Live"
    ),
    EcosystemApp(
        name = "Nava Studio",
        faName = "استودیو نوا",
        description = "AI-powered music composition and acoustic soundscape preservation.",
        faDescription = "استودیو هوشمند آهنگسازی و حفظ صداهای اصیل.",
        status = "Coming Soon"
    ),
    EcosystemApp(
        name = "Hamnava",
        faName = "هم‌نوا",
        description = "Collaborative artist space for Persian classical instrumentalists and vocalists.",
        faDescription = "فضای هم‌آفرینی برای نوازندگان و خوانندگان موسیقی اصیل.",
        status = "Coming Soon"
    ),
    EcosystemApp(
        name = "Ghese Go",
        faName = "قصه‌گو",
        description = "Interactive folklore and storytelling engine preserving oral tradition.",
        faDescription = "موتور هوشمند قصه گویی و حفظ ادبیات شفاهی.",
        status = "Coming Soon"
    ),
    EcosystemApp(
        name = "Afarinesh Hub",
        faName = "هاب آفرینش",
        description = "Ecosystem portal managing single sign-on, identity, and rewards.",
        faDescription = "درگاه مرکزی اکوسیستم برای مدیریت هویت و جوایز.",
        status = "Live"
    ),
    EcosystemApp(
        name = "Siavash Studio",
        faName = "استودیو سیاوش",
        description = "Creative digital media production and visual art studio.",
        faDescription = "استودیو تولید رسانه‌های دیجیتال و هنرهای بصری.",
        status = "Live"
    ),
    EcosystemApp(
        name = "Tavana Work",
        faName = "توانا ورک",
        description = "Decentralized job marketplace matching skilled craftsmen with global projects.",
        faDescription = "بازار کار غیرمتمرکز برای متخصصان و استادکاران.",
        status = "Planned"
    ),
    EcosystemApp(
        name = "Bazi Land",
        faName = "بازی لند",
        description = "Educational gamification platform bringing heritage culture to younger generations.",
        faDescription = "پلتفرم بازی‌سازی آموزشی برای آشنایی نسل جوان با فرهنگ.",
        status = "Planned"
    ),
    EcosystemApp(
        name = "Mehr Bank",
        faName = "بانک مهر",
        description = "Ethical micro-finance supporting rural carpet weavers and artisan guilds.",
        faDescription = "صندوق قرض‌الحسنه و حمایت از قالی‌بافان روستایی.",
        status = "Planned"
    ),
    EcosystemApp(
        name = "Unity Chain",
        faName = "یونیتی چین",
        description = "Blockchain provenance protocol verifying handmade rug origin and knot authenticity.",
        faDescription = "پروتکل بلاکچین برای اثبات اصالت و شناسه فرش دستباف.",
        status = "Planned"
    )
)

@Composable
fun EcosystemScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        // Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.Hub,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "FB New Meta Ecosystem",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "اکوسیستم شهر توانا و هاب آفرینش",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Banner Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Stars,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Unified Single Sign-On: One account grants access across all 11 platforms and enters you into monthly artisan support draws.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(ecosystemApps) { app ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = app.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "(${app.faName})",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }

                            val badgeColor = when (app.status) {
                                "Live" -> MaterialTheme.colorScheme.primary
                                "Coming Soon" -> MaterialTheme.colorScheme.secondary
                                else -> MaterialTheme.colorScheme.outline
                            }

                            Surface(
                                color = badgeColor.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = app.status,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = badgeColor,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }

                        Text(
                            text = app.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            text = app.faDescription,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}
