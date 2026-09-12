package com.example.farshbazar.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.farshbazar.data.model.*
import com.example.farshbazar.data.repository.FarshBazarRepository
import com.example.farshbazar.data.service.GeminiStoryService
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.UUID

class MainViewModel(val repository: FarshBazarRepository) : ViewModel() {

    init {
        viewModelScope.launch {
            repository.initializeDefaultDataIfNeeded()
        }
    }

    val carpets: StateFlow<List<Carpet>> = repository.allCarpets
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val vendors: StateFlow<List<Vendor>> = repository.allVendors
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val currentUser: StateFlow<UserProfile?> = repository.currentUser
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    // Gemini API Key Automation & Status
    val isGeminiLive: Boolean = GeminiStoryService.isApiKeyConfigured()
    val maskedApiKey: String = GeminiStoryService.getMaskedApiKey()

    // Vendor Search Filter
    private val _vendorSearchQuery = MutableStateFlow("")
    val vendorSearchQuery: StateFlow<String> = _vendorSearchQuery.asStateFlow()

    fun updateVendorSearchQuery(query: String) {
        _vendorSearchQuery.value = query
    }

    val filteredVendors: StateFlow<List<Vendor>> = combine(vendors, vendorSearchQuery) { vendorList, query ->
        if (query.isBlank()) {
            vendorList
        } else {
            val q = query.lowercase()
            vendorList.filter {
                it.name.lowercase().contains(q) ||
                it.location.lowercase().contains(q) ||
                it.bio.lowercase().contains(q) ||
                it.specialties.lowercase().contains(q)
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // AI Story Generator State
    private val _generatedStory = MutableStateFlow<String?>(null)
    val generatedStory: StateFlow<String?> = _generatedStory.asStateFlow()

    private val _isGeneratingStory = MutableStateFlow(false)
    val isGeneratingStory: StateFlow<Boolean> = _isGeneratingStory.asStateFlow()

    fun generateCarpetStory(type: String, style: String) {
        viewModelScope.launch {
            _isGeneratingStory.value = true
            _generatedStory.value = null
            
            val storyText = GeminiStoryService.generateCarpetStory(type, style)
            
            _generatedStory.value = storyText
            _isGeneratingStory.value = false
        }
    }

    // Actions
    fun addCarpet(name: String, price: String, imageUrl: String, description: String, vendorId: String, consignment: Boolean, onComplete: () -> Unit) {
        viewModelScope.launch {
            val newCarpet = Carpet(
                id = "carpet-" + UUID.randomUUID().toString().take(8),
                name = name,
                description = description,
                price = price,
                imageUrl = if (imageUrl.isBlank()) "https://images.unsplash.com/photo-1614080035039-af24354a6478?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&w=1080" else imageUrl,
                vendorId = vendorId,
                consignment = consignment
            )
            repository.addCarpet(newCarpet)
            onComplete()
        }
    }

    fun updateCarpet(carpet: Carpet, onComplete: () -> Unit) {
        viewModelScope.launch {
            repository.updateCarpet(carpet)
            onComplete()
        }
    }

    fun deleteCarpet(carpet: Carpet, onComplete: () -> Unit) {
        viewModelScope.launch {
            repository.deleteCarpet(carpet)
            onComplete()
        }
    }

    fun createVendorProfile(name: String, location: String, specialties: String, bio: String, userId: String, onComplete: (String) -> Unit) {
        viewModelScope.launch {
            val vendorId = "vendor-" + UUID.randomUUID().toString().take(8)
            val newVendor = Vendor(
                id = vendorId,
                name = name,
                location = location,
                specialties = specialties,
                avatarUrl = "https://images.unsplash.com/photo-1673898065178-df3f365e136f?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&w=1080",
                bio = bio,
                userId = userId,
                isVerified = false
            )
            repository.addVendor(newVendor)
            
            // Update current user profile to vendor status
            val user = currentUser.value
            if (user != null) {
                repository.updateUserProfile(user.copy(isVendor = true, vendorId = vendorId))
            }
            onComplete(vendorId)
        }
    }

    fun updateVendorProfile(vendor: Vendor, onComplete: () -> Unit) {
        viewModelScope.launch {
            repository.addVendor(vendor)
            onComplete()
        }
    }

    fun addReview(vendorId: String, reviewerName: String, rating: Int, comment: String, onComplete: () -> Unit) {
        viewModelScope.launch {
            val user = currentUser.value
            val review = Review(
                id = "review-" + UUID.randomUUID().toString().take(8),
                reviewerName = reviewerName.ifBlank { user?.displayName ?: "Enthusiast" },
                rating = rating,
                comment = comment,
                vendorId = vendorId,
                userId = user?.id ?: "guest-user"
            )
            repository.addReview(review)
            onComplete()
        }
    }

    fun submitSuggestion(text: String, userType: String, onComplete: () -> Unit) {
        viewModelScope.launch {
            val user = currentUser.value
            val suggestion = Suggestion(
                id = "sug-" + UUID.randomUUID().toString().take(8),
                suggestionText = text,
                userType = userType,
                userId = user?.id ?: "guest",
                userName = user?.displayName ?: "Guest User"
            )
            repository.submitSuggestion(suggestion)
            onComplete()
        }
    }

    fun toggleAuth() {
        viewModelScope.launch {
            val curr = currentUser.value
            if (curr != null) {
                repository.updateUserProfile(curr.copy(isLoggedIn = !curr.isLoggedIn))
            }
        }
    }
}

class MainViewModelFactory(private val repository: FarshBazarRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
