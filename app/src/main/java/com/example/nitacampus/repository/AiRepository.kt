package com.example.nitacampus.repository

import com.example.nitacampus.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel

class AiRepository {

    private val generativeModel = GenerativeModel(
        modelName = "gemini-2.5-flash",
        apiKey = BuildConfig.GEMINI_API_KEY
    )

    suspend fun askQuestion(question: String): String {

        return try {

            val response =
                generativeModel.generateContent(question)

            response.text ?: "No response from AI"

        } catch (e: Exception) {

            android.util.Log.e(
                "GEMINI_ERROR",
                e.stackTraceToString()
            )

            "Unable to connect to Gemini API."
        }
    }
}