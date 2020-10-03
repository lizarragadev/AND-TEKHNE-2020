package com.miramicodigo.listaspersonalizadas.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.miramicodigo.listaspersonalizadas.R
import com.miramicodigo.listaspersonalizadas.model.Pokemon
import kotlinx.android.synthetic.main.item_lista.view.*

class CustomAdapter(activity: Activity, data: ArrayList<Pokemon>) : BaseAdapter() {

    private val context: Context
    private val items: ArrayList<Pokemon>

    init {
        this.context = activity
        this.items = data
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Pokemon {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        val viewHolder : ViewHolder
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lista, parent, false)
            viewHolder = ViewHolder(convertView)
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
        }

        val poke = getItem(position)
        viewHolder.itemTitulo.text = poke.titulo
        viewHolder.itemSubtitulo.text = poke.subtitulo
        viewHolder.itemImagen.setImageResource(poke.imagen)

        return convertView
    }

    class ViewHolder(view: View) {
        var itemTitulo: TextView
        var itemSubtitulo: TextView
        var itemImagen: ImageView

        init {
            itemTitulo = view.findViewById(R.id.tvTitulo) as TextView
            itemSubtitulo = view.findViewById(R.id.tvSubtitulo) as TextView
            itemImagen = view.findViewById(R.id.ivImagen) as ImageView
        }
    }

}