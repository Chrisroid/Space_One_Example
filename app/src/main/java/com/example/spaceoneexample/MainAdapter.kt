package com.example.spaceoneexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceoneexample.MainAdapter.*
import com.example.spaceoneexample.api.Aircraft

class MainAdapter(private val data: List<Aircraft>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    inner class MainViewHolder(val view: View): RecyclerView.ViewHolder(view){
        fun bind(aircraft: Aircraft) {
            val name = view.findViewById<TextView>(R.id.name)
            val manufacturer = view.findViewById<TextView>(R.id.manufacturer)
            val manufacturerYear = view.findViewById<TextView>(R.id.manufacturerYear)

            name.text = aircraft.aircraftName
            manufacturer.text = aircraft.aircraftManufacturer
            manufacturerYear.text = aircraft.aircraftManufacturerYear


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return MainViewHolder(v)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}