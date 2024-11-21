package com.example.kotlin.examen_moviles.Frameworks.adapter

import android.util.Log
import com.example.kotlin.examen_moviles.Data.network.model.HistoricalEvent
import com.example.kotlin.examen_moviles.Frameworks.adapter.viewholder.HistoricalViewHolder
import com.example.kotlin.examen_moviles.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoricalAdapter(private var events: List<HistoricalEvent>) :
    RecyclerView.Adapter<HistoricalAdapter.HistoricalViewHolder>() {

    class HistoricalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.titleText)
        private val description: TextView = itemView.findViewById(R.id.descriptionText)
        private val date: TextView = itemView.findViewById(R.id.dateText)

        fun bind(event: HistoricalEvent) {
            title.text = event.category2 ?: "Unknown Country"
            description.text = event.description ?: "No description available"
            date.text = "Date: ${event.date ?: "Unknown date"}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoricalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_historical_event, parent, false)
        return HistoricalViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoricalViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount(): Int = events.size

    fun updateData(newData: List<HistoricalEvent>) {
        this.events = newData
        notifyDataSetChanged()
    }
}
