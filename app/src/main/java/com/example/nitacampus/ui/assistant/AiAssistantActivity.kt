package com.example.nitacampus.ui.assistant

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nitacampus.R
import com.example.nitacampus.adapter.MessageAdapter
import com.example.nitacampus.model.MessageModel
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.launch

class AiAssistantActivity : AppCompatActivity() {

    private lateinit var eTextMessage: EditText
    private lateinit var btnSend: ImageButton
    private lateinit var rvMessages: RecyclerView
    private lateinit var welcomeLayout: LinearLayout

    private lateinit var adapter: MessageAdapter
    private val messageList = ArrayList<MessageModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_assistant)

        eTextMessage = findViewById(R.id.eTextMessage)
        btnSend = findViewById(R.id.btnSend)
        rvMessages = findViewById(R.id.rvMessages)
        welcomeLayout = findViewById(R.id.welcomeLayout)

        adapter = MessageAdapter(messageList)

        rvMessages.layoutManager = LinearLayoutManager(this)
        rvMessages.adapter = adapter
        rvMessages.itemAnimator = DefaultItemAnimator()

        val generativeModel = GenerativeModel(
            modelName = "gemini-2.5-flash",
            apiKey = "AQ.Ab8RN6Iv-KSBmCr6BkulWE5u0W4FkQrSxzhDr84llzK3a6znBA"
        )

        btnSend.setOnClickListener {

            val question = eTextMessage.text.toString().trim()

            if (question.isEmpty()) {
                Toast.makeText(
                    this,
                    "Please enter a question",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // Hide welcome screen after first message
            welcomeLayout.visibility = View.GONE

            // Add user message
            messageList.add(
                MessageModel(
                    question,
                    true
                )
            )

            adapter.notifyItemInserted(messageList.size - 1)

            rvMessages.smoothScrollToPosition(
                messageList.size - 1
            )

            eTextMessage.text.clear()

            lifecycleScope.launch {

                try {

                    btnSend.isEnabled = false

                    // Typing indicator
                    val typingMessage = MessageModel(
                        "NITA AI is typing...",
                        false
                    )

                    messageList.add(typingMessage)

                    adapter.notifyItemInserted(
                        messageList.size - 1
                    )

                    rvMessages.smoothScrollToPosition(
                        messageList.size - 1
                    )

                    // Gemini Response
                    val response =
                        generativeModel.generateContent(question)

                    val answer =
                        response.text ?: "No response from AI"

                    // Remove typing message
                    messageList.remove(typingMessage)

                    adapter.notifyDataSetChanged()

                    // Add AI response
                    messageList.add(
                        MessageModel(
                            answer,
                            false
                        )
                    )

                    adapter.notifyItemInserted(
                        messageList.size - 1
                    )

                    rvMessages.smoothScrollToPosition(
                        messageList.size - 1
                    )

                } catch (e: Exception) {

                    Toast.makeText(
                        this@AiAssistantActivity,
                        e.message ?: "Unknown Error",
                        Toast.LENGTH_LONG
                    ).show()

                } finally {

                    btnSend.isEnabled = true
                }
            }
        }

        val tvHello = findViewById<TextView>(R.id.tvHallo)

        tvHello.text = "Hello 👋"
//
//        val userId = intent.getStringExtra("userId") ?: ""
//
//        if (userId.isNotEmpty()) {
//
//            val databaseReference = FirebaseDatabase.getInstance().getReference("Users")
//
//            databaseReference.child(userId).get()
//                .addOnSuccessListener { snapshot ->
//
//                    val name = snapshot.child("name")
//                        .getValue(String::class.java)
//
//                    tvHello.text = "Hello ${name ?: "User"} 👋"
//                }
//                .addOnFailureListener {
//
//                    tvHello.text = "Hello"
//                }
//        }

        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }
    }
}