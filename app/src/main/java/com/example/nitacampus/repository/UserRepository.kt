package com.example.nitacampus.repository

import com.example.nitacampus.model.Users
import com.google.firebase.database.FirebaseDatabase

class UserRepository {

    private val database =
        FirebaseDatabase.getInstance()
            .getReference("Users")

    fun registerUser(
        user: Users,
        callback: (Boolean, String?) -> Unit
    ) {

        val username = user.username

        if (username.isNullOrEmpty()) {
            callback(false, "Username cannot be empty")
            return
        }

        database.child(username)
            .get()
            .addOnSuccessListener { snapshot ->

                if (snapshot.exists()) {

                    callback(
                        false,
                        "Username already taken"
                    )

                } else {

                    database.child(username)
                        .setValue(user)
                        .addOnSuccessListener {

                            callback(
                                true,
                                null
                            )
                        }
                        .addOnFailureListener {

                            callback(
                                false,
                                it.message
                            )
                        }
                }
            }
            .addOnFailureListener {

                callback(
                    false,
                    it.message
                )
            }
    }

    fun getUser(
        username: String,
        callback: (Users?, String?) -> Unit
    ) {

        database.child(username)
            .get()
            .addOnSuccessListener { snapshot ->

                if (snapshot.exists()) {

                    val user =
                        snapshot.getValue(
                            Users::class.java
                        )

                    callback(
                        user,
                        null
                    )

                } else {

                    callback(
                        null,
                        "User not found"
                    )
                }
            }
            .addOnFailureListener {

                callback(
                    null,
                    it.message
                )
            }
    }

    fun updateLastLogin(
        username: String
    ) {

        database.child(username)
            .child("lastLogin")
            .setValue(
                System.currentTimeMillis()
            )
    }
}