package com.example.nitacampus.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.nitacampus.R
import com.example.nitacampus.model.Users
import com.example.nitacampus.ui.dashboard.MainActivity2
import com.example.nitacampus.viewmodel.AuthState
import com.example.nitacampus.viewmodel.AuthViewModel

class SignUp : AppCompatActivity() {

    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val btnSignUp = findViewById<Button>(R.id.signUpButton)
        val etName = findViewById<EditText>(R.id.etFullName)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val etUserName = findViewById<EditText>(R.id.etUserName)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        viewModel.authState.observe(this) { state ->

            when (state) {

                is AuthState.Loading -> {

                    Toast.makeText(
                        this,
                        "Registering...",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is AuthState.Success -> {

                    val prefs = getSharedPreferences(
                        "NITA_PREFS",
                        MODE_PRIVATE
                    )

                    prefs.edit()
                        .putBoolean("isLoggedIn", true)
                        .putString("username", state.username)
                        .apply()

                    Toast.makeText(
                        this,
                        "Registration Successful",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent =
                        Intent(this, MainActivity2::class.java)

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

        btnSignUp.setOnClickListener {

            val name = etName.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val username = etUserName.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // Name Validation
            if (name.isEmpty()) {
                etName.error = "Full name required"
                etName.requestFocus()
                return@setOnClickListener
            }

            if (name.length < 3) {
                etName.error = "Name must be at least 3 characters"
                etName.requestFocus()
                return@setOnClickListener
            }

            // Phone Validation
            if (phone.isEmpty()) {
                etPhone.error = "Phone number required"
                etPhone.requestFocus()
                return@setOnClickListener
            }

            if (!phone.matches(Regex("^[0-9]{10}$"))) {
                etPhone.error = "Enter a valid 10-digit phone number"
                etPhone.requestFocus()
                return@setOnClickListener
            }

            if (!phone.startsWith("6")
                && !phone.startsWith("7")
                && !phone.startsWith("8")
                && !phone.startsWith("9")
            ) {
                etPhone.error = "Enter valid mobile number"
                etPhone.requestFocus()
                return@setOnClickListener
            }

            // Username Validation
            if (username.isEmpty()) {
                etUserName.error = "Username required"
                etUserName.requestFocus()
                return@setOnClickListener
            }

            if (username.length < 4) {
                etUserName.error = "Username must be at least 4 characters"
                etUserName.requestFocus()
                return@setOnClickListener
            }

            if (username.length > 20) {
                etUserName.error = "Username too long"
                etUserName.requestFocus()
                return@setOnClickListener
            }

            if (username.contains(" ")) {
                etUserName.error = "Username cannot contain spaces"
                etUserName.requestFocus()
                return@setOnClickListener
            }

            // Password Validation
            if (password.isEmpty()) {
                etPassword.error = "Password required"
                etPassword.requestFocus()
                return@setOnClickListener
            }

            if (password.length < 6) {
                etPassword.error = "Password must be at least 6 characters"
                etPassword.requestFocus()
                return@setOnClickListener
            }

            if (!password.matches(Regex("^(?=.*[A-Za-z])(?=.*\\d).{6,}$"))) {
                etPassword.error =
                    "Password must contain letters and numbers"
                etPassword.requestFocus()
                return@setOnClickListener
            }

            val user = Users(
                name = name,
                phone = phone,
                username = username,
                password = password
            )

            viewModel.registerUser(user)
        }
    }
}