package com.miramicodigo.archivos_assets.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.miramicodigo.archivos_assets.R
import com.miramicodigo.archivos_assets.view.adapter.RVAdapter
import com.miramicodigo.archivos_assets.model.Producto

class MainActivity : AppCompatActivity() {

    val FILE_DATA = "products.txt"

    var datos = mutableListOf<Producto>()
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var adaptador: RVAdapter
    lateinit var activity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

}