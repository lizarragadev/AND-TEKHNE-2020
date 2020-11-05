package com.miramicodigo.archivos_assets.view.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miramicodigo.archivos_assets.R
import com.miramicodigo.archivos_assets.model.Producto

class RVAdapter(activity: Activity, private val items: MutableList<Producto>) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    private val context: Context
    private var itemFilters = mutableListOf<Producto>()

    init {
        this.context = activity
        itemFilters = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_lista, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = itemFilters[position]
        holder.tvDescripcion.text = producto.descripcion
        holder.tvCodigo.text = producto.codigo
        holder.tvUnidadVenta.text = producto.unidadVenta
        holder.tvUnidadesXCaja.text = producto.unidadesXCaja
        holder.tvLineaProd.text = producto.lineaProduccion
        holder.tvDisponibilidad.text = producto.disponibilidad
    }

    override fun getItemCount(): Int {
        return itemFilters.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDescripcion: TextView
        val tvCodigo: TextView
        val tvUnidadVenta: TextView
        val tvUnidadesXCaja: TextView
        val tvLineaProd: TextView
        val tvDisponibilidad: TextView


        init {
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion) as TextView
            tvCodigo = itemView.findViewById(R.id.tvCodigo) as TextView
            tvUnidadVenta = itemView.findViewById(R.id.tvUnidadVenta) as TextView
            tvUnidadesXCaja = itemView.findViewById(R.id.tvUnidadesCaja) as TextView
            tvLineaProd = itemView.findViewById(R.id.tvLineaProd) as TextView
            tvDisponibilidad = itemView.findViewById(R.id.tvDisponibilidad) as TextView
        }
    }

}