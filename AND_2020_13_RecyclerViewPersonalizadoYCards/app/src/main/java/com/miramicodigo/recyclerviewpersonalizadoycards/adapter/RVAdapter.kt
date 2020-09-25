package com.miramicodigo.recyclerviewpersonalizadoycards.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.miramicodigo.recyclerviewpersonalizadoycards.model.Pokemon

class RVAdapter(activity: Activity, private val items: ArrayList<Pokemon>) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    private val context: Context

    init {
        this.context = activity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return null!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {




    }
}