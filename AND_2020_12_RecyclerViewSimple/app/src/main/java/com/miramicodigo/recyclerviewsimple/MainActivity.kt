package com.miramicodigo.recyclerviewsimple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var listaDatos: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        llenarDatos()

        rvData.layoutManager = LinearLayoutManager(applicationContext)
        rvData.adapter = RecyclerViewAdapter(listaDatos)

    }

    private fun llenarDatos() {
        listaDatos = ArrayList(resources.getStringArray(R.array.paises).asList())
    }

    // Realiza el enlace o crea la referencia hacia el o los Views de cada nuestro item
    inner class RecyclerViewCustomHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTexto: TextView = itemView as TextView

        init {
            itemView.setOnClickListener {
                Toast.makeText(applicationContext, "Hizo click en ${listaDatos[adapterPosition]}", Toast.LENGTH_LONG).show()
            }
        }
    }

    inner class RecyclerViewAdapter(val data: ArrayList<String>) : RecyclerView.Adapter<RecyclerViewCustomHolder>() {

        // Asigna el XML a utilizar para crear la estructura de un ITEM de nuestra lista
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewCustomHolder {
            val layoutInflater = LayoutInflater.from(applicationContext)
            val view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false)
            return RecyclerViewCustomHolder(view)
        }

        // Una vez que ya tenemos el diseño de un View, solo queda asignarle datos.
        override fun onBindViewHolder(holder: RecyclerViewCustomHolder, position: Int) {
            val dato = data[position]
            holder.tvTexto.text = dato
        }

        override fun getItemCount(): Int {
            return data.size
        }
    }
}