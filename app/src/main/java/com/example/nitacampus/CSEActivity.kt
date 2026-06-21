package com.example.nitacampus

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CSEActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cseactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val back = findViewById<ImageView>(R.id.backBtn)

        val faculty = findViewById<LinearLayout>(R.id.faculty)
        val notes = findViewById<LinearLayout>(R.id.notes)
        val pyq = findViewById<LinearLayout>(R.id.pyq)
        val documents = findViewById<LinearLayout>(R.id.documents)

        faculty.setOnClickListener {
            val intent = Intent(this, NextActivity::class.java)
            startActivity(intent)
        }

        back.setOnClickListener {
            finish()
        }

//        notes.setOnClickListener {
//            val intent = Intent(this, NotesActivity::class.java)
//            startActivity(intent)
//        }
//
//        pyq.setOnClickListener {
//            val intent = Intent(this, Activity::class.java)
//            startActivity(intent)
//        }
//
//        documents.setOnClickListener {
//            val intent = Intent(this, DocumentsActivity::class.java)
//            startActivity(intent)
//        }
    }
}