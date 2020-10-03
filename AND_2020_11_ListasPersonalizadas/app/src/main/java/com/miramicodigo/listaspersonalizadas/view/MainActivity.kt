package com.miramicodigo.listaspersonalizadas.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miramicodigo.listaspersonalizadas.R
import com.miramicodigo.listaspersonalizadas.adapter.CustomAdapter
import com.miramicodigo.listaspersonalizadas.model.Pokemon
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var datos: ArrayList<Pokemon>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        llenarPokemones()

        val adaptador = CustomAdapter(this, datos)
        lvLista.adapter = adaptador
    }

    private fun llenarPokemones() {
        datos = ArrayList()
        val arrayTitulos = resources.getStringArray(R.array.titulos)
        val arraySubtitulos = resources.getStringArray(R.array.subtitulos)
        val arrayImagenes = resources.obtainTypedArray(R.array.imagenes)

        // rangos [1, 2, 3, 4, 2, 2, 1, 0]
        // rango ABCDEF.....XYZ

        for (i in arrayTitulos.indices) {
            val poke = Pokemon()
            poke.titulo = arrayTitulos[i]
            poke.subtitulo = arraySubtitulos[i]
            poke.imagen = arrayImagenes.getResourceId(i, -1)
            datos.add(poke)
        }
    }
















}