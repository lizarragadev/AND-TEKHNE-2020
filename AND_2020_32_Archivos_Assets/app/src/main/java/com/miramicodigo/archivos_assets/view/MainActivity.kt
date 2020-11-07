package com.miramicodigo.archivos_assets.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miramicodigo.archivos_assets.R
import com.miramicodigo.archivos_assets.view.adapter.RVAdapter
import com.miramicodigo.archivos_assets.model.Producto
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    val FILE_DATA = "products.txt"

    var datos = mutableListOf<Producto>()
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var adaptador: RVAdapter
    lateinit var activity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val res = getDataFromAssets()
        if(res) {
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            rvResults.layoutManager = layoutManager
            adaptador = RVAdapter(this, datos)
            rvResults.adapter = adaptador
        }

    }

    private fun getDataFromAssets(): Boolean {
        try {
            val br = BufferedReader(InputStreamReader(assets.open(FILE_DATA), "UTF-8"))
            val list = br.readLines()
            for(line in list) {
                val split = line.split(";")
                val prod = Producto()
                prod.codigo = split[0]
                prod.descripcion = split[1]
                prod.unidadVenta = split[2]
                prod.unidadesXCaja = split[3]
                prod.lineaProduccion = split[4]
                prod.disponibilidad = split[5]
                datos.add(prod)
            }
            br.close()
            return true
        } catch (ex: IOException) {
            println(ex.message)
            return false
        }
    }

}