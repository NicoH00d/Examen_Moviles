package com.example.kotlin.examen_moviles.Frameworks.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.examen_moviles.Data.network.model.HistoricalEvent
import com.example.kotlin.examen_moviles.R

class HistoricalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title: TextView = itemView.findViewById(R.id.titleText)
    private val description: TextView = itemView.findViewById(R.id.descriptionText)
    private val date: TextView = itemView.findViewById(R.id.dateText)

    fun bind(event: HistoricalEvent) {
        title.text = event.category2 ?: "pais Desconocido"
        description.text = event.description ?: "No hay descripcion"
        date.text = "Fecha: ${event.date ?: "Fecha desconocida"}"
    }
}
