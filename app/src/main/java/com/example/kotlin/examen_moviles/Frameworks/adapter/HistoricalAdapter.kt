package com.example.kotlin.examen_moviles.Frameworks.adapter

import android.util.Log
import com.example.kotlin.examen_moviles.Data.network.model.HistoricalEvent
import com.example.kotlin.examen_moviles.Frameworks.adapter.viewholder.HistoricalViewHolder
import com.example.kotlin.examen_moviles.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HistoricalAdapter(private val events: List<HistoricalEvent>) :
    RecyclerView.Adapter<HistoricalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoricalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_historical_event, parent, false)
        return HistoricalViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoricalViewHolder, position: Int) {
        Log.d("Adapter", "Binding event: ${events[position]}")
        holder.bind(events[position])
    }

    override fun getItemCount(): Int = events.size
}
