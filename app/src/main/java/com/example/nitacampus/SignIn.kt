package com.example.nitacampus

import android.content.Intent
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {

    lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val signUp = findViewById<TextView>(R.id.tvSignUp)

        signUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            val cardView = findViewById<MaterialCardView>(R.id.materialCardView)
//            cardView.setRenderEffect(
//                RenderEffect.createBlurEffect(30f, 30f, Shader.TileMode.CLAMP)
//            )
//        }

        val login = findViewById<Button>(R.id.button3)
        val username = findViewById<TextInputEditText>(R.id.etName)
        val password = findViewById<TextInputEditText>(R.id.etPassword)

        login.setOnClickListener {
            val uniqueId = username.text.toString().trim()
            val enteredPassword = password.text.toString().trim()

            if (uniqueId.isEmpty()) {
                Toast.makeText(this, "Enter username", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (enteredPassword.isEmpty()) {
                Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            readData(uniqueId, enteredPassword)
        }
    }

    private fun readData(uniqueId: String, enteredPassword: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(uniqueId).get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val storedPassword = snapshot.child("password").value.toString()

                if (storedPassword == enteredPassword) {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity2::class.java)
                    intent.putExtra("userId", uniqueId)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Username doesn't exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
    }
}
