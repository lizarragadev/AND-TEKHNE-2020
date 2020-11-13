package com.miramicodigo.restful_covid.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miramicodigo.restful_covid.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_scrolling.*

class MainActivity : AppCompatActivity() {

    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var adaptador: RecyclerView.Adapter<*>
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        collapsingTL.title = "COVID APP"
        context = this


        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvDatos.layoutManager = layoutManager

        obtenerDatos()
    }

    private fun obtenerDatos() {


    }

}