package com.example.nitacampus.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nitacampus.R
import com.example.nitacampus.ui.dashboard.MainActivity2
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.FirebaseDatabase
import java.util.concurrent.TimeUnit

class SignIn : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var verificationId: String

    private val callbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(
                credential: PhoneAuthCredential
            ) {

                auth.signInWithCredential(credential)
                    .addOnSuccessListener {

                        Toast.makeText(
                            this@SignIn,
                            "Login Successful",
                            Toast.LENGTH_SHORT
                        ).show()

                        startActivity(
                            Intent(
                                this@SignIn,
                                MainActivity2::class.java
                            )
                        )

                        finish()
                    }
            }

            override fun onVerificationFailed(
                e: FirebaseException
            ) {

                android.util.Log.e(
                    "OTP_ERROR",
                    "Verification failed",
                    e
                )

                Toast.makeText(
                    this@SignIn,
                    "Error: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {

                this@SignIn.verificationId =
                    verificationId

                Toast.makeText(
                    this@SignIn,
                    "OTP Sent",
                    Toast.LENGTH_SHORT
                ).show()

                showOtpDialog()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)

        auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null) {

            startActivity(
                Intent(
                    this,
                    MainActivity2::class.java
                )
            )

            finish()
            return
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        val signUp = findViewById<TextView>(R.id.tvSignUp)
        val phoneEditText = findViewById<TextInputEditText>(R.id.etPhone)
        val sendOtpButton = findViewById<Button>(R.id.btnSendOtp)

        signUp.setOnClickListener {
            verifySuccessfulLogin()
        }

        sendOtpButton.setOnClickListener {

            val username =
                findViewById<TextInputEditText>(
                    R.id.etName
                ).text.toString().trim()

            val phone =
                phoneEditText.text.toString().trim()

            if (username.isEmpty()) {

                Toast.makeText(
                    this,
                    "Enter Username",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (phone.isEmpty()) {

                phoneEditText.error =
                    "Enter phone number"

                return@setOnClickListener
            }

            FirebaseDatabase.getInstance()
                .reference
                .child("Users")
                .child(username)
                .get()
                .addOnSuccessListener { snapshot ->

                    if (!snapshot.exists()) {

                        Toast.makeText(
                            this,
                            "User not found",
                            Toast.LENGTH_SHORT
                        ).show()

                        return@addOnSuccessListener
                    }

                    val storedPhone =
                        snapshot.child("phone")
                            .getValue(String::class.java)

                    if (storedPhone == phone) {

                        sendOtp("+91$phone")

                    } else {

                        Toast.makeText(
                            this,
                            "Phone number does not match username",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                .addOnFailureListener {

                    Toast.makeText(
                        this,
                        it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }

    private fun verifySuccessfulLogin() {

        val username =
            findViewById<TextInputEditText>(
                R.id.etName
            ).text.toString().trim()

        FirebaseDatabase.getInstance()
            .reference
            .child("Users")
            .child(username)
            .child("lastLogin")
            .setValue(System.currentTimeMillis())

        Toast.makeText(
            this,
            "Login Successful",
            Toast.LENGTH_SHORT
        ).show()

        startActivity(
            Intent(
                this,
                MainActivity2::class.java
            )
        )

        finish()
    }
    private fun sendOtp(phone: String) {

        val options =
            PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phone)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(callbacks)
                .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun showOtpDialog() {

        val view =
            layoutInflater.inflate(
                R.layout.dialog_otp,
                null
            )

        val etOtp =
            view.findViewById<EditText>(
                R.id.etOtp
            )

        AlertDialog.Builder(this)
            .setTitle("Verify OTP")
            .setView(view)
            .setPositiveButton("Verify") { _, _ ->

                val code =
                    etOtp.text.toString().trim()

                verifyOtp(code)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun verifyOtp(code: String) {

        val credential =
            PhoneAuthProvider.getCredential(
                verificationId,
                code
            )

        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    val username =
                        findViewById<TextInputEditText>(
                            R.id.etName
                        ).text.toString().trim()

                    FirebaseDatabase.getInstance()
                        .reference
                        .child("Users")
                        .child(username)
                        .child("lastLogin")
                        .setValue(System.currentTimeMillis())

                    Toast.makeText(
                        this,
                        "Login Successful",
                        Toast.LENGTH_SHORT
                    ).show()

                    startActivity(
                        Intent(
                            this,
                            MainActivity2::class.java
                        )
                    )

                    finish()

                } else {

                    Toast.makeText(
                        this,
                        "Invalid OTP",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }



}