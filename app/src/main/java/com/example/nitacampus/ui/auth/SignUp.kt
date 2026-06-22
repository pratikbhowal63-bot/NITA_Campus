package com.example.nitacampus.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.nitacampus.ui.dashboard.MainActivity2
import com.example.nitacampus.R
import com.example.nitacampus.model.Users
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
        val etEnrollment = findViewById<EditText>(R.id.etEnrollment)

        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        viewModel.authState.observe(this) { state ->

            when(state){

                is AuthState.Loading -> {

                    Toast.makeText(
                        this,
                        "Registering...",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is AuthState.Success -> {

                    Toast.makeText(
                        this,
                        "Registration Successful",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent = Intent(this, MainActivity2::class.java)

                    intent.putExtra("userId", state.username)
                    startActivity(intent)
                    finish()
                }

                is AuthState.Error -> {

                    Toast.makeText(
                        this,
                        state.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        btnSignUp.setOnClickListener {

            val name =
                etName.text.toString().trim()

            val phone =
                etPhone.text.toString().trim()

            val username =
                etUserName.text.toString().trim()

            val enrollment =
                etEnrollment.text.toString().trim()

            if(name.isEmpty() ||
                phone.isEmpty() ||
                username.isEmpty() ||
                enrollment.isEmpty()) {

                Toast.makeText(
                    this,
                    "Fill all fields",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val user = Users(
                name = name,
                phone = phone,
                username = username,
                enrollmentNumber = enrollment
            )

            viewModel.registerUser(user)
        }
    }
}