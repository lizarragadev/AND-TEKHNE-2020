package com.miramicodigo.sqlite.ui

import android.os.Bundle
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.miramicodigo.sqlite.db.DatabaseAdapter
import com.miramicodigo.sqlite.R
import com.miramicodigo.sqlite.model.Persona
import kotlinx.android.synthetic.main.activity_detalle.*

class DetalleActivity : AppCompatActivity() {

    private var id = 0
    private lateinit var db: DatabaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        title = "Detalle"

        db = DatabaseAdapter(this)
        db.abrir()

        id = intent.getIntExtra("id", 0)
        val cursor = db.obtenerPersona(id)

        if(cursor.moveToFirst()) {
            tvNombre.text = cursor.getString(1)
            tvTelefono.text = cursor.getString(2)
            tvCorreo.text = cursor.getString(3)

            val genero = cursor.getString(4)
            if(genero == "f") {
                tvGenero.text = "Fememino"
                ivImagen.setImageResource(R.drawable.woman)
            } else {
                tvGenero.text = "Masculino"
                ivImagen.setImageResource(R.drawable.man)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_detalle, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_editar -> {
                val intent = Intent(this, FormularioActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
                finish()
            }
            R.id.menu_eliminar -> {
                confirmaEliminacion().show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun confirmaEliminacion(): AlertDialog {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Eliminar persona")
            .setMessage("¿Estás seguro de eliminar la persona?")
            .setCancelable(false)
            .setPositiveButton("Si") { dInt, i ->
                db.eliminarPersona(id)
                finish()
                Toast.makeText(this, "Se borró exitosamente", Toast.LENGTH_SHORT).show()
            }
            .setNeutralButton("Cancelar") { dInt, i ->
            }

        return alertDialog.create()
    }

    override fun onStop() {
        super.onStop()
        db.cerrar()
    }

}
