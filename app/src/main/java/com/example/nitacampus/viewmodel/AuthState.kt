package com.example.nitacampus.viewmodel

sealed class AuthState {

    object Loading : AuthState()

    data class Success(
        val username: String
    ) : AuthState()

    data class Error(
        val message: String
    ) : AuthState()
}