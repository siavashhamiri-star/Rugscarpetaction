package com.example.farshbazar.data.local

import androidx.room.*
import com.example.farshbazar.data.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FarshBazarDao {
    // Carpets
    @Query("SELECT * FROM carpets")
    fun getAllCarpets(): Flow<List<Carpet>>

    @Query("SELECT * FROM carpets WHERE vendorId = :vendorId")
    fun getCarpetsByVendor(vendorId: String): Flow<List<Carpet>>

    @Query("SELECT * FROM carpets WHERE id = :id")
    suspend fun getCarpetById(id: String): Carpet?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCarpet(carpet: Carpet)

    @Delete
    suspend fun deleteCarpet(carpet: Carpet)

    @Query("DELETE FROM carpets WHERE id = :id")
    suspend fun deleteCarpetById(id: String)

    // Vendors
    @Query("SELECT * FROM vendors")
    fun getAllVendors(): Flow<List<Vendor>>

    @Query("SELECT * FROM vendors WHERE id = :id")
    suspend fun getVendorById(id: String): Vendor?

    @Query("SELECT * FROM vendors WHERE id = :id")
    fun observeVendorById(id: String): Flow<Vendor?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVendor(vendor: Vendor)

    // Reviews
    @Query("SELECT * FROM reviews WHERE vendorId = :vendorId ORDER BY createdAt DESC")
    fun getReviewsForVendor(vendorId: String): Flow<List<Review>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReview(review: Review)

    // Suggestions / Feedback
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSuggestion(suggestion: Suggestion)

    // User Profile
    @Query("SELECT * FROM user_profile WHERE id = 'current_user'")
    fun getCurrentUser(): Flow<UserProfile?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserProfile(userProfile: UserProfile)

    // Pre-population helper counts
    @Query("SELECT COUNT(*) FROM vendors")
    suspend fun getVendorCount(): Int
}
