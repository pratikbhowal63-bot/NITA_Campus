package com.example.nitacampus.model

data class Users(
    var name: String? = null,
    var phone: String? = null,
    var password: String? = null,
    var uid: String? = null,
    var username: String? = null,
    var points: Int = 0,
    var streak: Int = 0,
    var badge: String? = "Beginner",
    var lastLogin: Long = System.currentTimeMillis()
)