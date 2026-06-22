package com.example.nitacampus.ui.profile

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.nitacampus.R
import com.example.nitacampus.viewmodel.UserViewModel

class profile : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel

    private lateinit var tvName: TextView
    private lateinit var tvRoll: TextView
    private lateinit var tvPoints: TextView
    private lateinit var tvStreak: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        tvName = findViewById(R.id.tvName)
        tvRoll = findViewById(R.id.tvRoll)
        tvPoints = findViewById(R.id.Points)
        tvStreak = findViewById(R.id.Streak)

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        viewModel.user.observe(this) { user ->

            tvName.text = user.name ?: "Unknown User"

            tvRoll.text = user.username ?: "N/A"

            tvPoints.text = user.points.toString()

            tvStreak.text = "${user.streak} days"
        }

        val username =
            intent.getStringExtra("username")
                ?: intent.getStringExtra("userId")
                ?: ""

        Log.d("PROFILE", "Received username = $username")

        if (username.isEmpty()) {

            tvName.text = "User Not Found"

        } else {
            viewModel.loadUser(username)
        }

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v, insets ->

            val systemBars =
                insets.getInsets(
                    WindowInsetsCompat.Type.systemBars()
                )

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }
    }
}