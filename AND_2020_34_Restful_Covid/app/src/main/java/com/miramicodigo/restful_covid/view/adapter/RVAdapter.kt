package com.miramicodigo.restful_covid.view.adapter

import android.content.Context
import android.content.Intent
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.miramicodigo.restful_covid.R
import com.miramicodigo.restful_covid.model.Country
import com.miramicodigo.restful_covid.view.CountryActivity

class RVAdapter(private val context: Context, val data: ArrayList<Country>) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    private val dataset: ArrayList<Country> = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_countries, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = dataset[position]

        holder.paisTextView.text = country.country
        holder.totalCasosTextView.text = country.totalConfirmed.toString()
        holder.totalCasosHoyTextView.text = country.newConfirmed.toString()
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val paisTextView: TextView = itemView.findViewById(R.id.tvPais)
        val totalCasosTextView: TextView = itemView.findViewById(R.id.tvCasosConfirmados)
        val totalCasosHoyTextView: TextView = itemView.findViewById(R.id.tvCasosConfirmadosHOY)

    }
}