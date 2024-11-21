package com.example.kotlin.examen_moviles.Frameworks.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.examen_moviles.Data.network.model.HistoricalEvent
import com.example.kotlin.examen_moviles.Data.network.model.RetrofitClient
import com.example.kotlin.examen_moviles.Data.repositories.HistoricalRepository
import kotlinx.coroutines.launch

class HistoricalViewModel : ViewModel() {

    private val repository = HistoricalRepository(RetrofitClient.apiService)

    private val _events = MutableLiveData<List<HistoricalEvent>>()
    val events: LiveData<List<HistoricalEvent>> get() = _events

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private var currentPage = 1
    private val limit = 10 // Número de elementos por página
    private var isLastPage = false // Indica si ya no hay más datos

    fun fetchEvents() {
        if (_isLoading.value == true || isLastPage) return

        _isLoading.value = true
        viewModelScope.launch {
            val result = repository.fetchHistoricalEvents(currentPage, limit)
            result.onSuccess { newEvents ->
                if (newEvents.isEmpty()) {
                    isLastPage = true
                } else {
                    val currentList = _events.value ?: emptyList()
                    _events.value = currentList + newEvents
                    currentPage++
                }
                _isLoading.value = false
            }.onFailure {
                _isLoading.value = false
                Log.e("ViewModel", "Error fetching events: ${it.message}")
            }
        }
    }
}

