package com.miramicodigo.listassimples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val paises = resources.getStringArray(R.array.paises)

        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, paises)

        lvListaDatos.adapter = adaptador

        lvListaDatos.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this, "Hizo click en ${paises[i]}", Toast.LENGTH_LONG).show()
        }

    }
}