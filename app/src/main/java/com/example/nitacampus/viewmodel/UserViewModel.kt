package com.example.nitacampus.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nitacampus.model.Users
import com.example.nitacampus.repository.UserRepository

class UserViewModel : ViewModel() {

    private val repository = UserRepository()

    private val _user = MutableLiveData<Users>()
    val user: LiveData<Users> = _user

    fun loadUser(username: String) {

        repository.getUser(username) { user, _ ->

            user?.let {
                _user.value = it
            }
        }
    }
}