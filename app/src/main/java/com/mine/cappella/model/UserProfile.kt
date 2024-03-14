package com.mine.cappella.model

data class UserProfile(
    val dob: String,
    val gender: String,
    val id: String,
    val live_listen_status: Boolean,
    val name: String,
    val profile_picture: String
)