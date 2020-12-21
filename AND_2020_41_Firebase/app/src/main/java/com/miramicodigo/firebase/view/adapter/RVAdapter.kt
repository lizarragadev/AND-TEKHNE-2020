package com.miramicodigo.firebase.view.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miramicodigo.firebase.R
import com.miramicodigo.firebase.model.Contact
import com.miramicodigo.firebase.view.DetailContactActivity

class RVAdapter(activity: Activity, private val items: ArrayList<Contact>) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    private val context: Context

    init {
        this.context = activity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val obj = items[position]

        holder.tvTitulo.text = obj.nombre
        holder.tvSubtitulo.text = obj.correo
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitulo: TextView
        val tvSubtitulo: TextView

        init {
            tvTitulo = itemView.findViewById(R.id.tvTitulo) as TextView
            tvSubtitulo = itemView.findViewById(R.id.tvSubtitulo) as TextView

            itemView.setOnClickListener {
                val intent = Intent(context, DetailContactActivity::class.java)
                intent.putExtra("contact", items[adapterPosition])
                context.startActivity(intent)
            }
        }
    }
}