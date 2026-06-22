package com.example.nitacampus.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nitacampus.repository.AiRepository
import kotlinx.coroutines.launch

class AiViewModel : ViewModel() {
    private val repository = AiRepository()
    private val _response = MutableLiveData<String>()
    val response: LiveData<String> = _response

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun askQuestion(question: String) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = repository.askQuestion(question)
            _response.value = result
            _isLoading.value = false
        }
    }
}