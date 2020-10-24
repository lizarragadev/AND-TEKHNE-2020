package com.miramicodigo.menus

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var datos: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPopup.setOnClickListener(this)

        llenarDatos()
        lvDatos.adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, datos)

        registerForContextMenu(lvDatos)
    }

    fun llenarDatos() {
        datos = ArrayList()
        for (i in 0..49) {
            datos.add("Elemento " + (i + 1) + " de la lista.")
        }
    }

    override fun onClick(view: View) {
        var menu = PopupMenu(this, btnPopup)
        menu.menuInflater.inflate(R.menu.menu_popup, menu.menu)
        menu.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId) {
                R.id.popupUno -> {
                    Toast.makeText(applicationContext, "Click en popup 1", Toast.LENGTH_SHORT).show()
                    return@setOnMenuItemClickListener true
                }
                R.id.popupDos -> {
                    Toast.makeText(applicationContext, "Click en popup 2", Toast.LENGTH_SHORT).show()
                    return@setOnMenuItemClickListener true
                }
                else -> return@setOnMenuItemClickListener false
            }
        }
        menu.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_opciones, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menuUno -> Toast.makeText(this, "Click en opcion 1", Toast.LENGTH_SHORT).show()
            R.id.menuDos -> Toast.makeText(this, "Click en opcion 2", Toast.LENGTH_SHORT).show()
            R.id.menuTres -> Toast.makeText(this, "Click en opcion 3", Toast.LENGTH_SHORT).show()
            R.id.menuCuatro -> Toast.makeText(this, "Click en opcion 4", Toast.LENGTH_SHORT).show()
            R.id.subMenuUno -> Toast.makeText(this, "Click en submenu 1", Toast.LENGTH_SHORT).show()
            R.id.subMenuDos -> Toast.makeText(this, "Click en submenu 2", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        menuInflater.inflate(R.menu.menu_contextual, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val index = item.menuInfo as AdapterView.AdapterContextMenuInfo
        when(item.itemId) {
            R.id.contextUno -> Toast.makeText(this, "Obtener enlace de item ${index.position + 1}", Toast.LENGTH_SHORT).show()
            R.id.contextDos -> Toast.makeText(this, "Descargar imagen de item ${index.position + 1}", Toast.LENGTH_SHORT).show()
            R.id.contextTres -> Toast.makeText(this, "Eliminar item ${index.position + 1}", Toast.LENGTH_SHORT).show()
        }
        return super.onContextItemSelected(item)
    }

}