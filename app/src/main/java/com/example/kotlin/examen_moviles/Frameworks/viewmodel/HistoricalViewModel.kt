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

    // LiveData para los eventos históricos
    private val _events = MutableLiveData<List<HistoricalEvent>>()
    val events: LiveData<List<HistoricalEvent>> get() = _events

    // LiveData para manejar errores
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    // LiveData para mostrar el estado de carga
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    // Método para obtener eventos históricos
    fun fetchEvents(page: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            Log.d("ViewModel", "Fetching events for page $page")
            val result = repository.fetchHistoricalEvents(page)
            result.onSuccess { events ->
                Log.d("ViewModel", "Fetched events: $events")
                _events.value = events
                _isLoading.value = false
            }.onFailure { throwable ->
                Log.e("ViewModel", "Error: ${throwable.message}")
                _error.value = "Failed to load events."
                _isLoading.value = false
            }
        }
    }

}
