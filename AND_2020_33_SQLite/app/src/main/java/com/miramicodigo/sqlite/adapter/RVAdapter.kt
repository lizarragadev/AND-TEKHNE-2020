package com.miramicodigo.archivos_assets.view.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miramicodigo.sqlite.R
import com.miramicodigo.sqlite.model.Persona
import com.miramicodigo.sqlite.ui.DetalleActivity

class RVAdapter(activity: Activity) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    var context: Context
    var items: MutableList<Persona>

    init {
        context = activity
        items = mutableListOf()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_lista, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val persona = items[position]
        holder.tvPrincipalItem.text = persona.nombre
        holder.tvSecundarioItem.text = persona.correo
        if(persona.genero == "f")
            holder.ivImageItem.setImageResource(R.drawable.woman)
        else
            holder.ivImageItem.setImageResource(R.drawable.man)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addPersonas(lista: ArrayList<Persona>) {
        items.clear()
        items.addAll(lista)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivImageItem: ImageView
        val tvPrincipalItem: TextView
        val tvSecundarioItem: TextView

        init {
            ivImageItem = itemView.findViewById(R.id.ivImageItem) as ImageView
            tvPrincipalItem = itemView.findViewById(R.id.tvPrincipalItem) as TextView
            tvSecundarioItem = itemView.findViewById(R.id.tvSecundarioItem) as TextView

            itemView.setOnClickListener {
                val intent = Intent(context, DetalleActivity::class.java)
                intent.putExtra("id", items[adapterPosition].id)
                context.startActivity(intent)
            }
        }

    }

}