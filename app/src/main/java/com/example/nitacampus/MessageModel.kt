package com.example.nitacampus

data class MessageModel(
    val message: String,
    val isUser: Boolean,
    var isTyping: Boolean = false
)