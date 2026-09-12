package com.example.farshbazar.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "carpets")
data class Carpet(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val price: String,
    val imageUrl: String,
    val vendorId: String,
    val consignment: Boolean = false
)

@Serializable
@Entity(tableName = "vendors")
data class Vendor(
    @PrimaryKey val id: String,
    val name: String,
    val location: String,
    val specialties: String, // Comma separated for room storage
    val avatarUrl: String,
    val bio: String,
    val userId: String,
    val isVerified: Boolean = false
) {
    val specialtiesList: List<String>
        get() = specialties.split(",").map { it.trim() }.filter { it.isNotEmpty() }
}

@Serializable
@Entity(tableName = "reviews")
data class Review(
    @PrimaryKey val id: String,
    val reviewerName: String,
    val reviewerImage: String = "",
    val rating: Int,
    val comment: String,
    val vendorId: String,
    val userId: String,
    val createdAt: Long = System.currentTimeMillis()
)

@Serializable
@Entity(tableName = "suggestions")
data class Suggestion(
    @PrimaryKey val id: String,
    val suggestionText: String,
    val userType: String, // 'buyer' or 'vendor'
    val userId: String,
    val userName: String,
    val createdAt: Long = System.currentTimeMillis()
)

@Serializable
@Entity(tableName = "user_profile")
data class UserProfile(
    @PrimaryKey val id: String = "current_user",
    val displayName: String,
    val email: String,
    val photoUrl: String = "",
    val isVendor: Boolean = false,
    val vendorId: String? = null,
    val isLoggedIn: Boolean = true
)

data class EcosystemApp(
    val name: String,
    val faName: String,
    val description: String,
    val faDescription: String,
    val status: String // "Live", "Coming Soon", "Planned"
)

data class BilingualContent(
    val fa: String,
    val en: String
)

data class ManifestoChapter(
    val titleFa: String,
    val titleEn: String,
    val paragraphs: List<BilingualContent>
)
