package com.example.kotlin.examen_moviles.Frameworks.adapter.viewholder


import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.examen_moviles.Data.network.model.HistoricalEvent
import com.example.kotlin.examen_moviles.R


class HistoricalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title: TextView = itemView.findViewById(R.id.eventTitle)
    private val date: TextView = itemView.findViewById(R.id.eventDate)

    fun bind(event: HistoricalEvent) {
        title.text = event.category2 ?: "Unknown Title"
        date.text = event.date ?: "Unknown Date"
    }
}
