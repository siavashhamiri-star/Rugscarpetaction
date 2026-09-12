package com.example.farshbazar.data.repository

import com.example.farshbazar.data.local.FarshBazarDao
import com.example.farshbazar.data.model.*
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class FarshBazarRepository(private val dao: FarshBazarDao) {

    val allCarpets: Flow<List<Carpet>> = dao.getAllCarpets()
    val allVendors: Flow<List<Vendor>> = dao.getAllVendors()
    val currentUser: Flow<UserProfile?> = dao.getCurrentUser()

    fun getCarpetsByVendor(vendorId: String): Flow<List<Carpet>> =
        dao.getCarpetsByVendor(vendorId)

    fun observeVendorById(id: String): Flow<Vendor?> =
        dao.observeVendorById(id)

    suspend fun getVendorById(id: String): Vendor? =
        dao.getVendorById(id)

    suspend fun getCarpetById(id: String): Carpet? =
        dao.getCarpetById(id)

    fun getReviewsForVendor(vendorId: String): Flow<List<Review>> =
        dao.getReviewsForVendor(vendorId)

    suspend fun addCarpet(carpet: Carpet) {
        dao.insertCarpet(carpet)
    }

    suspend fun updateCarpet(carpet: Carpet) {
        dao.insertCarpet(carpet)
    }

    suspend fun deleteCarpet(carpet: Carpet) {
        dao.deleteCarpet(carpet)
    }

    suspend fun deleteCarpetById(id: String) {
        dao.deleteCarpetById(id)
    }

    suspend fun addVendor(vendor: Vendor) {
        dao.insertVendor(vendor)
    }

    suspend fun addReview(review: Review) {
        dao.insertReview(review)
    }

    suspend fun submitSuggestion(suggestion: Suggestion) {
        dao.insertSuggestion(suggestion)
    }

    suspend fun updateUserProfile(userProfile: UserProfile) {
        dao.insertUserProfile(userProfile)
    }

    suspend fun initializeDefaultDataIfNeeded() {
        if (dao.getVendorCount() == 0) {
            // Seed default vendors
            val vendor1 = Vendor(
                id = "vendor-1",
                name = "Isfahan Carpet Masters",
                location = "Isfahan, Iran",
                specialties = "Classic Isfahan, Nain, Fine Silk",
                avatarUrl = "https://images.unsplash.com/photo-1673898065178-df3f365e136f?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&w=1080",
                bio = "Generations of master weavers bringing you authentic Isfahan masterpieces crafted with pure wool and silk highlights.",
                userId = "user-vendor-1",
                isVerified = true
            )

            val vendor2 = Vendor(
                id = "vendor-2",
                name = "Tabriz Silk Weavers",
                location = "Tabriz, Iran",
                specialties = "Tabriz Medallion, Fine Silk Qum, Antique Floral",
                avatarUrl = "https://images.unsplash.com/photo-1719154717749-0d05f61a0588?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&w=1080",
                bio = "Specializing in high knot count silk carpets, intricate medallion designs, and rare Persian heirlooms.",
                userId = "user-vendor-2",
                isVerified = true
            )

            val vendor3 = Vendor(
                id = "vendor-3",
                name = "Modern Gabbeh Designs",
                location = "Shiraz, Iran",
                specialties = "Nomadic Gabbeh, Tribal Baluchi, Minimalist Natural",
                avatarUrl = "https://images.unsplash.com/photo-1544723795-3fb6469f5b39?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&w=1080",
                bio = "Contemporary and tribal handwoven carpets featuring vivid vegetable dyes and rustic nomadic expressions.",
                userId = "user-vendor-3",
                isVerified = false
            )

            dao.insertVendor(vendor1)
            dao.insertVendor(vendor2)
            dao.insertVendor(vendor3)

            // Seed default carpets
            val carpets = listOf(
                Carpet(
                    id = "carpet-1",
                    name = "The Royal Azure Medallion",
                    description = "A breathtaking Isfahan masterwork featuring a brilliant indigo medallion surrounded by fine floral palmettes woven with silk accents.",
                    price = "$2,450",
                    imageUrl = "https://images.unsplash.com/photo-1614080035039-af24354a6478?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&w=1080",
                    vendorId = "vendor-1",
                    consignment = true
                ),
                Carpet(
                    id = "carpet-2",
                    name = "Golden Silk Qum Floral",
                    description = "100% pure silk Qum carpet with soft ivory and golden tones, showcasing exquisite knotting precision.",
                    price = "$3,800",
                    imageUrl = "https://images.unsplash.com/photo-1583100913831-3d318552c7e4?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&w=1080",
                    vendorId = "vendor-2",
                    consignment = false
                ),
                Carpet(
                    id = "carpet-3",
                    name = "Classic Tabriz Crimson Medallion",
                    description = "Hand-knotted Tabriz rug with a traditional central medallion and rich crimson red border frame.",
                    price = "$1,890",
                    imageUrl = "https://images.unsplash.com/photo-1756361771453-6bce7c2cf539?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&w=1080",
                    vendorId = "vendor-2",
                    consignment = true
                ),
                Carpet(
                    id = "carpet-4",
                    name = "Blue Nain Fine Wool & Silk",
                    description = "A pristine Nain carpet with deep cobalt blue field, intricate Shah Abbasi flowers, and high density knotting.",
                    price = "$2,100",
                    imageUrl = "https://images.unsplash.com/photo-1600166943013-b30f3d5f57d6?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&w=1080",
                    vendorId = "vendor-1",
                    consignment = false
                ),
                Carpet(
                    id = "carpet-5",
                    name = "Sunlit Shiraz Gabbeh",
                    description = "Vibrant tribal Gabbeh woven with naturally dyed warm yellow and burnt orange wool, carrying simple nomadic motifs.",
                    price = "$950",
                    imageUrl = "https://images.unsplash.com/photo-1631466882094-d4af58ef7025?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&w=1080",
                    vendorId = "vendor-3",
                    consignment = false
                ),
                Carpet(
                    id = "carpet-6",
                    name = "Baluchi Geometric Tree of Life",
                    description = "Authentic Baluchi rug featuring dark charcoal and rust hues with geometric motifs and fringe detail.",
                    price = "$780",
                    imageUrl = "https://images.unsplash.com/photo-1761639502675-442d396c516f?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&w=1080",
                    vendorId = "vendor-3",
                    consignment = false
                )
            )

            carpets.forEach { dao.insertCarpet(it) }

            // Seed sample reviews
            val reviews = listOf(
                Review(
                    id = "review-1",
                    reviewerName = "Alexander M.",
                    rating = 5,
                    comment = "Incredible authenticity and craftsmanship! The Isfahan carpet arrived in pristine condition.",
                    vendorId = "vendor-1",
                    userId = "user-buyer-1"
                ),
                Review(
                    id = "review-2",
                    reviewerName = "Elena R.",
                    rating = 5,
                    comment = "The silk quality on the Qum carpet is beyond words. Truly an investment piece.",
                    vendorId = "vendor-2",
                    userId = "user-buyer-2"
                )
            )
            reviews.forEach { dao.insertReview(it) }

            // Seed default user profile
            dao.insertUserProfile(
                UserProfile(
                    id = "current_user",
                    displayName = "Siavash Hamiri",
                    email = "siavashhamiri@gmail.com",
                    isVendor = true,
                    vendorId = "vendor-1",
                    isLoggedIn = true
                )
            )
        }
    }
}
