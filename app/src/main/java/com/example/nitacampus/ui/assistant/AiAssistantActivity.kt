package com.example.nitacampus.ui.assistant

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nitacampus.BuildConfig
import com.example.nitacampus.R
import com.example.nitacampus.adapter.MessageAdapter
import com.example.nitacampus.model.MessageModel
import com.example.nitacampus.viewmodel.AiViewModel

class AiAssistantActivity : AppCompatActivity() {

    private lateinit var eTextMessage: EditText
    private lateinit var btnSend: ImageButton
    private lateinit var btnBack: ImageButton
    private lateinit var rvMessages: RecyclerView
    private lateinit var welcomeLayout: LinearLayout
    private lateinit var tvHello: TextView

    private lateinit var adapter: MessageAdapter
    private lateinit var viewModel: AiViewModel

    private val messageList = ArrayList<MessageModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_assistant)

        initViews()
        setupRecyclerView()
        setupViewModel()
        setupListeners()

        android.util.Log.d(
            "API_KEY_TEST",
            BuildConfig.GEMINI_API_KEY
        )
    }

    private fun initViews() {

        eTextMessage = findViewById(R.id.eTextMessage)
        btnSend = findViewById(R.id.btnSend)
        btnBack = findViewById(R.id.btnBack)
        rvMessages = findViewById(R.id.rvMessages)
        welcomeLayout = findViewById(R.id.welcomeLayout)
        tvHello = findViewById(R.id.tvHallo)

        tvHello.text = "Hello 👋"
    }

    private fun setupRecyclerView() {

        adapter = MessageAdapter(messageList)

        rvMessages.layoutManager =
            LinearLayoutManager(this)

        rvMessages.adapter = adapter

        rvMessages.itemAnimator =
            DefaultItemAnimator()
    }

    private fun setupViewModel() {

        viewModel =
            ViewModelProvider(this)[AiViewModel::class.java]

        viewModel.response.observe(this) { answer ->

            if (messageList.isNotEmpty() &&
                messageList.last().message == "NITA AI is typing..."
            ) {

                messageList.removeAt(
                    messageList.size - 1
                )

                adapter.notifyItemRemoved(
                    messageList.size
                )
            }

            messageList.add(
                MessageModel(
                    answer,
                    false
                )
            )

            adapter.notifyItemInserted(
                messageList.size - 1
            )

            scrollToBottom()

            btnSend.isEnabled = true
        }
    }

    private fun setupListeners() {

        btnSend.setOnClickListener {

            val question =
                eTextMessage.text.toString().trim()

            if (question.isEmpty()) {

                Toast.makeText(
                    this,
                    "Please enter a question",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            welcomeLayout.visibility = View.GONE

            messageList.add(
                MessageModel(
                    question,
                    true
                )
            )

            adapter.notifyItemInserted(
                messageList.size - 1
            )

            scrollToBottom()

            eTextMessage.text.clear()

            btnSend.isEnabled = false

            messageList.add(
                MessageModel(
                    "NITA AI is typing...",
                    false
                )
            )

            adapter.notifyItemInserted(
                messageList.size - 1
            )

            scrollToBottom()

            viewModel.askQuestion(question)
        }

        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun scrollToBottom() {

        rvMessages.smoothScrollToPosition(
            messageList.size - 1
        )
    }
}