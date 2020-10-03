package com.miramicodigo.recyclerviewpersonalizadoycards.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miramicodigo.recyclerviewpersonalizadoycards.R
import com.miramicodigo.recyclerviewpersonalizadoycards.model.Pokemon
import com.miramicodigo.recyclerviewpersonalizadoycards.ui.DetalleActivity

class RVAdapter(activity: Activity, private val items: ArrayList<Pokemon>) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    private val context: Context

    init {
        this.context = activity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_item_grid, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val poke = items[position]

        val fontBold = Typeface.createFromAsset(context.assets, "product_sans_bold.ttf")
        val fontReg = Typeface.createFromAsset(context.assets, "product_sans_regular.ttf")

        holder.tvTitulo.typeface = fontBold
        holder.tvSubtitulo.typeface = fontReg

        holder.tvTitulo.text = poke.titulo
        holder.tvSubtitulo.text = poke.subtitulo
        holder.ivImagen.setImageResource(poke.imagen)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitulo: TextView
        val tvSubtitulo: TextView
        val ivImagen: ImageView

        init {
            tvTitulo = itemView.findViewById(R.id.tvTitulo) as TextView
            tvSubtitulo = itemView.findViewById(R.id.tvSubtitulo) as TextView
            ivImagen = itemView.findViewById(R.id.ivImagen) as ImageView

            itemView.setOnClickListener {
                val intent = Intent(context, DetalleActivity::class.java)
                intent.putExtra("poke", items[adapterPosition])
                context.startActivity(intent)
            }
        }
    }
}