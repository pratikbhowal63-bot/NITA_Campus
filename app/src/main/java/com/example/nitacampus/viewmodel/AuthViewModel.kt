package com.example.nitacampus.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nitacampus.model.Users
import com.example.nitacampus.repository.UserRepository

class AuthViewModel : ViewModel() {

    private val repository = UserRepository()

    private val _authState = MutableLiveData<AuthState>()

    val authState: LiveData<AuthState> get() = _authState

    fun registerUser(user: Users) {

        _authState.value = AuthState.Loading

        repository.registerUser(user) { success, error ->

            if (success) {

                _authState.value =
                    AuthState.Success(
                        user.username ?: ""
                    )

            } else {

                _authState.value =
                    AuthState.Error(
                        error ?: "Unknown Error"
                    )
            }
        }
    }
    fun login(
        username: String,
        password: String
    ) {

        _authState.value = AuthState.Loading

        repository.login(username, password) { success, error ->

            if (success) {

                _authState.value =
                    AuthState.Success(username)

            } else {

                _authState.value =
                    AuthState.Error(
                        error ?: "Unknown Error"
                    )
            }
        }
    }
}