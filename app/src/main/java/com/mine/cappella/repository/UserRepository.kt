package com.mine.cappella.repository

import com.mine.cappella.model.UserProfile
import com.mine.cappella.service.ApiService

class UserRepository(val apiService: ApiService) {
    suspend fun getUserProfile(id: String): UserProfile {
        return apiService.getUserProfile(id)
    }
}
