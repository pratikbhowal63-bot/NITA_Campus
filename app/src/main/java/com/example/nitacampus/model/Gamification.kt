package com.example.nitacampus.model

import com.google.firebase.database.FirebaseDatabase

class GamificationManager {

    private val usersRef =
        FirebaseDatabase.getInstance().getReference("Users")

    fun addPoints(uid: String, pointsToAdd: Int) {

        usersRef.child(uid)
            .child("points")
            .get()
            .addOnSuccessListener { snapshot ->

                var current = snapshot.getValue(Int::class.java) ?: 0

                current += pointsToAdd

                usersRef.child(uid)
                    .child("points")
                    .setValue(current)

                updateBadge(uid, current)
            }
    }

    private fun updateBadge(uid: String, points: Int) {

        val badge = when {
            points >= 1000 -> "Campus Hero"
            points >= 500 -> "Contributor"
            points >= 100 -> "Active Student"
            else -> "Beginner"
        }

        usersRef.child(uid)
            .child("badge")
            .setValue(badge)
    }

    fun updateStreak(uid: String) {

        usersRef.child(uid)
            .get()
            .addOnSuccessListener { snapshot ->

                val lastLogin =
                    snapshot.child("lastLogin")
                        .getValue(Long::class.java) ?: 0L

                var streak =
                    snapshot.child("streak")
                        .getValue(Int::class.java) ?: 0

                var points =
                    snapshot.child("points")
                        .getValue(Int::class.java) ?: 0

                val now = System.currentTimeMillis()

                val oneDay =
                    24L * 60L * 60L * 1000L

                val difference = now - lastLogin

                if (difference >= oneDay &&
                    difference < oneDay * 2
                ) {

                    // Next day login
                    streak++
                    points += 5

                    usersRef.child(uid)
                        .child("points")
                        .setValue(points)

                    updateBadge(uid, points)

                    usersRef.child(uid)
                        .child("streak")
                        .setValue(streak)

                    usersRef.child(uid)
                        .child("lastLogin")
                        .setValue(now)

                } else if (difference >= oneDay * 2) {

                    // Streak broken
                    streak = 1
                    points += 5

                    usersRef.child(uid)
                        .child("points")
                        .setValue(points)

                    updateBadge(uid, points)

                    usersRef.child(uid)
                        .child("streak")
                        .setValue(streak)

                    usersRef.child(uid)
                        .child("lastLogin")
                        .setValue(now)
                }
            }
    }
}