package com.mine.cappella.service

import com.mine.cappella.model.UserProfile
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    @GET("api/user/baby-profile-test/{id}")
    suspend fun getUserProfile(@Path("id") id: String): UserProfile
    @GET("api/user/baby-profile-test/e71b3147-01f5-42bb/")
    suspend fun updateProfile(@Path("uuid") id: String,
                              @Part("gender") gender: RequestBody,
                              @Part("name") name: RequestBody,
                              @Part("dob") dob: RequestBody,
                              @Part profile_picture: MultipartBody.Part)
}