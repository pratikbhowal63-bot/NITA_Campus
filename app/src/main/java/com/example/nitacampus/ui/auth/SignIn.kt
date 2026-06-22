package com.example.nitacampus.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.nitacampus.R
import com.example.nitacampus.ui.dashboard.MainActivity2
import com.example.nitacampus.viewmodel.AuthState
import com.example.nitacampus.viewmodel.AuthViewModel
import com.google.android.material.textfield.TextInputEditText

class SignIn : AppCompatActivity() {

    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)

        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(
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

        val signUp = findViewById<TextView>(R.id.tvSignUp)
        val loginButton = findViewById<Button>(R.id.button3)

        val emailEditText = findViewById<TextInputEditText>(R.id.etName)

        val passwordEditText = findViewById<TextInputEditText>(R.id.etPassword)

        signUp.setOnClickListener {
            startActivity(
                Intent(this, SignUp::class.java)
            )
        }

        viewModel.authState.observe(this) { state ->

            when (state) {
                is AuthState.Loading -> {

                    Toast.makeText(
                        this,
                        "Logging in...",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is AuthState.Success -> {

                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, MainActivity2::class.java)
                    intent.putExtra("userId", state.username)
                    startActivity(intent)
                    finish()
                }

                is AuthState.Error -> {

                    Toast.makeText(
                        this,
                        state.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        loginButton.setOnClickListener {

            val email =
                emailEditText.text.toString().trim()

            val password =
                passwordEditText.text.toString().trim()

            if (email.isEmpty()) {
                emailEditText.error = "Enter Email"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                passwordEditText.error = "Enter Password"
                return@setOnClickListener
            }

            viewModel.login(
                email,
                password
            )
        }
    }
}