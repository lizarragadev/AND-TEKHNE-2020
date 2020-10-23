package com.miramicodigo.menus

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var datos: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPopup.setOnClickListener(this)

        llenarDatos()
        lvDatos.adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, datos)
    }

    fun llenarDatos() {
        datos = ArrayList()
        for (i in 0..49) {
            datos.add("Elemento " + (i + 1) + " de la lista.")
        }
    }

    override fun onClick(view: View) {

    }


}