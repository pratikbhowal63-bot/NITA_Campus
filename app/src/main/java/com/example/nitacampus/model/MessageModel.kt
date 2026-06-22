package com.example.nitacampus.model

data class MessageModel(
    val message: String,
    val isUser: Boolean,
    var isTyping: Boolean = false
)