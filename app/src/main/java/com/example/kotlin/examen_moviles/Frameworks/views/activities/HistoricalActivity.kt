package com.example.kotlin.examen_moviles.Frameworks.views.activities

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.examen_moviles.Frameworks.adapter.HistoricalAdapter
import com.example.kotlin.examen_moviles.Frameworks.viewmodel.HistoricalViewModel
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.examen_moviles.databinding.ActivityHistoricalBinding


class HistoricalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoricalBinding
    private lateinit var viewModel: HistoricalViewModel
    private lateinit var adapter: HistoricalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View Binding
        binding = ActivityHistoricalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ViewModel
        viewModel = ViewModelProvider(this)[HistoricalViewModel::class.java]

        // Adapter
        adapter = HistoricalAdapter(emptyList())

        // Setup RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Observe LiveData
        observeViewModel()

        // Load initial data
        viewModel.fetchEvents()

        // Load more button
        binding.loadMoreButton.setOnClickListener {
            viewModel.fetchEvents() // Cargar la siguiente página
        }
    }

    private fun observeViewModel() {
        viewModel.events.observe(this) { events ->
            adapter.updateData(events) // Actualiza los datos en el adaptador
        }
    }
}
