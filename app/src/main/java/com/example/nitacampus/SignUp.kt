package com.example.nitacampus

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
class SignUp : AppCompatActivity() {
    lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val btnSignUp = findViewById<Button>(R.id.signUpButton)
        val etName = findViewById<EditText>(R.id.etFullName)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val etUserName = findViewById<EditText>(R.id.etUserName)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        btnSignUp.setOnClickListener {
            val name = etName.text.toString()
            val phone = etPhone.text.toString()
            val password = etPassword.text.toString()
            val userId = etUserName.text.toString()


            val user = Users(name, phone, userId, password)

            database = FirebaseDatabase.getInstance().getReference("Users")

            database.child(userId).get().addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    Toast.makeText(this, "Username already taken", Toast.LENGTH_SHORT).show()
                }
                else {
                    database.child(userId).setValue(user)
                        .addOnSuccessListener {
                            Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show()

                            val intent = Intent(this, MainActivity2::class.java)
                            intent.putExtra("userId", userId)
                            startActivity(intent)
                            finish()
                        }.addOnFailureListener {
                            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                        }
                }
            }
        }
    }
}
