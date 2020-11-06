package com.miramicodigo.sqlite.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miramicodigo.archivos_assets.view.adapter.RVAdapter
import com.miramicodigo.sqlite.R
import com.miramicodigo.sqlite.db.DatabaseAdapter
import com.miramicodigo.sqlite.model.Persona
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var db: DatabaseAdapter
    var datos = ArrayList<Persona>()
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var adaptador: RVAdapter
    lateinit var activity: Activity

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Personas"
        activity = this
        layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        rvData.layoutManager = layoutManager
        adaptador = RVAdapter(activity)
        rvData.adapter = adaptador



    }

    override fun onStart() {
        super.onStart()

    }

    fun cargarDatosLista() {


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_adicionar -> {


            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStop() {
        super.onStop()

    }

}
