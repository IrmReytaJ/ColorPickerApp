package com.example.colorpickerapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ColorAdapter(private val colors: List<ColorEntity>) :
    RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    inner class ColorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val colorBox: View = view.findViewById(R.id.colorBox)
        val hexText: TextView = view.findViewById(R.id.hexText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_color, parent, false)
        return ColorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val color = colors[position]
        holder.colorBox.setBackgroundColor(Color.rgb(color.red, color.green, color.blue))
        holder.hexText.text = color.hex
    }

    override fun getItemCount(): Int = colors.size
}
