package com.example.farshbazar.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.farshbazar.data.model.BilingualContent
import com.example.farshbazar.data.model.ManifestoChapter

val manifestoChapters = listOf(
    ManifestoChapter(
        titleFa = "فصل اول: پژواک یک رویا",
        titleEn = "Chapter I: Echo of a Dream",
        paragraphs = listOf(
            BilingualContent(
                fa = "این راه با یک گوشی ساده Redmi Note 8 و دستی خالی از امکانات مالی، اما دلی لبریز از ایمان آغاز شد. در تاریک‌ترین لحظات، ایمان به توانستن چراغ راه ما در ظلمت بود.",
                en = "This path began with a simple Redmi Note 8 phone and empty pockets, but a heart overflowing with faith. In the darkest moments, belief in possibility was our light through the shadows."
            ),
            BilingualContent(
                fa = "فرش دستباف ایران فقط یک کالا نیست؛ گنجینه‌ای از هنر، عشق و زحمت سرپنجه‌های اصیل استادکارانی است که هنرشان در انزوا مانده بود.",
                en = "Iranian handwoven carpet is not merely a commodity; it is a treasure of art, devotion, and master craftsmanship that stood isolated from the global stage."
            )
        )
    ),
    ManifestoChapter(
        titleFa = "فصل دوم: فلسفه کیهانی آفرینش",
        titleEn = "Chapter II: Cosmic Philosophy of Creation",
        paragraphs = listOf(
            BilingualContent(
                fa = "ما معتقدیم هر گره‌ای که بر تار و پود فرش زده می‌شود، بازتابی از انضباط کیهانی است. هنر واقعی مرزهای جغرافیایی را در می‌نوردد و دل‌ها را به هم پیوند می‌دهد.",
                en = "We believe every knot tied to the loom reflects cosmic harmony. True artistry transcends geographical borders and binds hearts together."
            ),
            BilingualContent(
                fa = "اکوسیستم ما بنا شده تا واسطه‌های غیرضروری را حذف کرده و ارزش واقعی را به دستان زحمت‌کش بافنده بازگرداند.",
                en = "Our ecosystem is built to bypass unnecessary middlemen and restore genuine value directly to the hardworking hands of weavers."
            )
        )
    ),
    ManifestoChapter(
        titleFa = "فصل سوم: شهر توانا",
        titleEn = "Chapter III: Tavana City",
        paragraphs = listOf(
            BilingualContent(
                fa = "شهر توانا نماد مدینه‌ی فاضله‌ی کارآفرینی و دانایی است؛ جایی که دانش نظری به توانمندی عملی تبدیل می‌شود.",
                en = "Tavana City is the vision of entrepreneurial wisdom, where theoretical knowledge transforms directly into practical empowerment."
            )
        )
    ),
    ManifestoChapter(
        titleFa = "فصل چهارم: سوگندنامه اصالت",
        titleEn = "Chapter IV: The Creed of Authenticity",
        paragraphs = listOf(
            BilingualContent(
                fa = "ما متعهد می‌شویم تا پای جان از اصالت، هویت و کرامت هنرمندان ایرانی دفاع کنیم و صدای آنان را به اقصی نقاط جهان برسانیم.",
                en = "We pledge with all our soul to champion authenticity, dignity, and heritage for Iranian artisans, magnifying their voice across the entire globe."
            )
        )
    )
)

@Composable
fun ManifestoScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Hero Manifesto Header
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.MenuBook,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(48.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "کتاب آفرینش - منشور اصالت",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "The Book of Creation & Manifesto",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center
                )
            }
        }

        // Quote Card (Founder Origin)
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    imageVector = Icons.Default.FormatQuote,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(36.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "\"From a single Redmi Note 8 phone to a global movement connecting carpet artisans with the world, faith moved mountains when capital was zero.\"",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "— Siavash Hamiri (Founder & Architect)",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                    )
                }
            }
        }

        // Chapters List
        manifestoChapters.forEach { chapter ->
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
                        text = chapter.titleFa,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = chapter.titleEn,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Divider()

                    chapter.paragraphs.forEach { p ->
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(
                                text = p.fa,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = p.en,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}
