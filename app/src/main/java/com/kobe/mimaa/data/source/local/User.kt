package com.kobe.mimaa.data.source.local


data class User(
    val id : String? = null,
    val email: String = "",
    val password: String = "",
    val username: String = "",
    val profilePictureUrl: String? = null,
)