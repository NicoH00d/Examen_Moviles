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

        // ScrollListener for pagination
        setupScrollListener()

        // Initial data load
        viewModel.fetchEvents() // Load the first page
    }

    private fun observeViewModel() {
        viewModel.events.observe(this) { events ->
            Log.d("Activity", "Updating RecyclerView with ${events.size} items.")
            adapter.updateData(events) // Update the adapter's data
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) RecyclerView.VISIBLE else RecyclerView.GONE
        }
    }

    private fun setupScrollListener() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!viewModel.isLoading.value!! &&
                    (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0)
                ) {
                    Log.d("Activity", "Fetching next page...")
                    viewModel.fetchEvents() // Fetch the next page
                }
            }
        })
    }
}
