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

        // Auto Login Check
        val prefs = getSharedPreferences(
            "NITA_PREFS",
            MODE_PRIVATE
        )

        val isLoggedIn =
            prefs.getBoolean(
                "isLoggedIn",
                false
            )

        if (isLoggedIn) {

            val username =
                prefs.getString(
                    "username",
                    ""
                )

            val intent =
                Intent(
                    this,
                    MainActivity2::class.java
                )

            intent.putExtra(
                "userId",
                username
            )

            startActivity(intent)
            finish()
            return
        }

        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v, insets ->

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

        val signUp =
            findViewById<TextView>(R.id.tvSignUp)

        val loginButton =
            findViewById<Button>(R.id.button3)

        val usernameEditText =
            findViewById<TextInputEditText>(R.id.etName)

        val passwordEditText =
            findViewById<TextInputEditText>(R.id.etPassword)

        signUp.setOnClickListener {

            startActivity(
                Intent(this,
                    SignUp::class.java
                )
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

                    val preferences =
                        getSharedPreferences(
                            "NITA_PREFS",
                            MODE_PRIVATE
                        )

                    preferences.edit()
                        .putBoolean(
                            "isLoggedIn",
                            true
                        )
                        .putString(
                            "username",
                            state.username
                        )
                        .apply()

                    Toast.makeText(
                        this,
                        "Login Successful",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent =
                        Intent(
                            this,
                            MainActivity2::class.java
                        )

                    intent.putExtra(
                        "userId",
                        state.username
                    )

                    startActivity(intent)
                    finish()
                }

                is AuthState.Error -> {

                    Toast.makeText(
                        this,
                        "Error: ${state.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        loginButton.setOnClickListener {

            val username =
                usernameEditText.text.toString().trim()

            val password =
                passwordEditText.text.toString().trim()

            if (username.isEmpty()) {

                usernameEditText.error =
                    "Username is required"

                usernameEditText.requestFocus()

                return@setOnClickListener
            }

            if (username.length < 4) {

                usernameEditText.error =
                    "Username must be at least 4 characters"

                usernameEditText.requestFocus()

                return@setOnClickListener
            }

            if (password.isEmpty()) {

                passwordEditText.error =
                    "Password is required"

                passwordEditText.requestFocus()

                return@setOnClickListener
            }

            if (password.length < 6) {

                passwordEditText.error =
                    "Password must be at least 6 characters"

                passwordEditText.requestFocus()

                return@setOnClickListener
            }

            viewModel.login(
                username,
                password
            )
        }
    }
}