package com.example.nitacampus.repository

import com.example.nitacampus.model.Users
import com.google.firebase.database.FirebaseDatabase

class ProfileRepository {

    private val database =
        FirebaseDatabase.getInstance()
            .getReference("Users")

    fun getUser(
        username: String,
        callback: (Users?) -> Unit
    ) {

        database.child(username)
            .get()
            .addOnSuccessListener {

                callback(
                    it.getValue(
                        Users::class.java
                    )
                )
            }
            .addOnFailureListener {

                callback(null)
            }
    }
}