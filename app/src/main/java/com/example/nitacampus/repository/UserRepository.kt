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

        user.username?.let { database.child(it) }?.get()
            ?.addOnSuccessListener { snapshot ->

                if (snapshot.exists()) {

                    callback(
                        false,
                        "Username already taken"
                    )

                } else {

                    database.child(user.username!!)
                        .setValue(user)
                        .addOnSuccessListener {

                            callback(true, null)

                        }
                        .addOnFailureListener {

                            callback(
                                false,
                                it.message
                            )
                        }
                }
            }
            ?.addOnFailureListener {

                callback(
                    false,
                    it.message
                )
            }
    }

    fun login(username: String, password: String, callback: (Boolean, String?) -> Unit) {

        database.child(username).get()
            .addOnSuccessListener { snapshot ->

                if (!snapshot.exists()) {

                    callback(
                        false,
                        "Username doesn't exist"
                    )
                    return@addOnSuccessListener
                }

                val user =
                    snapshot.getValue(Users::class.java)

                if (user?.password == password) {

                    callback(true, null)

                } else {
                    callback(
                        false,
                        "Incorrect password"
                    )
                }
            }
            .addOnFailureListener {

                callback(
                    false,
                    it.message
                )
            }
    }
}