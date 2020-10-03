package com.miramicodigo.recyclerviewpersonalizadoycards.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.miramicodigo.recyclerviewpersonalizadoycards.R
import com.miramicodigo.recyclerviewpersonalizadoycards.adapter.RVAdapter
import com.miramicodigo.recyclerviewpersonalizadoycards.model.Pokemon
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var datos: ArrayList<Pokemon>
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var adaptador: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        llenarPokemones()

        //layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //layoutManager = GridLayoutManager(this, 2)
        layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        rvContenido.layoutManager = layoutManager

        adaptador = RVAdapter(this, datos)
        rvContenido.adapter = adaptador


    }

    private fun llenarPokemones() {
        datos = ArrayList()
        val arrayTitulos = resources.getStringArray(R.array.titulos)
        val arraySubtitulos = resources.getStringArray(R.array.subtitulos)
        val arrayImagenes = resources.obtainTypedArray(R.array.imagenes)

        for (i in arrayTitulos.indices) {
            val poke = Pokemon()
            poke.titulo = arrayTitulos[i]
            poke.subtitulo = arraySubtitulos[i]
            poke.imagen = arrayImagenes.getResourceId(i, -1)
            datos.add(poke)
        }
    }
}