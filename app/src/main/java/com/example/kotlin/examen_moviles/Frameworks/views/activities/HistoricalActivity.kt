package com.example.kotlin.examen_moviles.Frameworks.views.activities

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.examen_moviles.Frameworks.adapter.HistoricalAdapter
import com.example.kotlin.examen_moviles.Frameworks.viewmodel.HistoricalViewModel
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.kotlin.examen_moviles.databinding.ActivityHistoricalBinding

class HistoricalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoricalBinding
    private val viewModel: HistoricalViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoricalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.events.observe(this) { events ->
            if (events.isNotEmpty()) {
                Log.d("Activity", "Updating RecyclerView with ${events.size} items.")
                binding.recyclerView.adapter = HistoricalAdapter(events)
            } else {
                Log.d("Activity", "No events to display.")
                binding.errorText.text = "No historical events found."
            }
        }




        viewModel.error.observe(this) { error ->
            binding.errorText.text = error
        }

        viewModel.fetchEvents(1) // Empezar pag 1
    }
}
