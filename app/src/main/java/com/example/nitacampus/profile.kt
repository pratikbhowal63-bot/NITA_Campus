package com.example.nitacampus

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase

class profile : AppCompatActivity() {

    private lateinit var tvName: TextView
    private lateinit var tvRoll: TextView
    private lateinit var tvPoints: TextView
    private lateinit var tvStreak: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        tvName = findViewById(R.id.tvName)
        tvRoll = findViewById(R.id.tvRoll)
        tvPoints = findViewById(R.id.Points)
        tvStreak = findViewById(R.id.Streak)

        val username =
            intent.getStringExtra("username")
                ?: intent.getStringExtra("userId")
                ?: ""

        if (username.isEmpty()) {
            tvName.text = "User Not Found"
            return
        }
        loadProfile(username)
    }

    private fun loadProfile(username: String) {

        tvName.text = "Loading..."

        FirebaseDatabase.getInstance()
            .getReference("Users")
            .child(username)
            .get()
            .addOnSuccessListener { snapshot ->

                Log.d("PROFILE", snapshot.value.toString())

                if (!snapshot.exists()) {
                    tvName.text = "User Not Found"
                    return@addOnSuccessListener
                }

                val name = snapshot.child("name")
                        .getValue(String::class.java) ?: "N/A"

                val password =
                    snapshot.child("password")
                        .getValue(String::class.java) ?: "N/A"

                val points =
                    snapshot.child("points")
                        .value?.toString() ?: "0"

                val streak =
                    snapshot.child("streak")
                        .value?.toString() ?: "0"

                tvName.text = name
                tvRoll.text = password
                tvPoints.text = points
                tvStreak.text = "$streak days"
            }
            .addOnFailureListener { exception ->

                Log.e("PROFILE", exception.message ?: "Unknown Error")

                tvName.text = "Error Loading Profile"
                tvRoll.text = "--"
                tvPoints.text = "0"
                tvStreak.text = "0 days"
            }
    }
}