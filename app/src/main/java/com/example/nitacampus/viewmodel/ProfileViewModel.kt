package com.example.nitacampus.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nitacampus.model.Users
import com.example.nitacampus.repository.ProfileRepository

class ProfileViewModel : ViewModel() {

    private val repository = ProfileRepository()

    private val _user = MutableLiveData<Users>()
    val user: LiveData<Users> = _user

    fun loadProfile(username: String) {

        repository.getUser(username) { user ->

            user?.let {
                _user.value = it
            }
        }
    }
}