package com.miramicodigo.sqlite.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.miramicodigo.sqlite.db.DatabaseAdapter
import com.miramicodigo.sqlite.R
import com.miramicodigo.sqlite.model.Persona
import kotlinx.android.synthetic.main.activity_formulario.*

class FormularioActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var db: DatabaseAdapter
    private var edicion = false
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        db = DatabaseAdapter(this)
        db.abrir()

        if(intent.extras != null) {
            edicion = true
            title = "Editar persona"
            id = intent.getIntExtra("id", 0)
            val cursor = db.obtenerPersona(id)
            if(cursor.moveToFirst()) {
                val persona = Persona()
                persona.nombre = cursor.getString(1)
                persona.telefono = cursor.getString(2)
                persona.correo = cursor.getString(3)
                persona.genero = cursor.getString(4)

                etNombre.setText(persona.nombre)
                etTelefono.setText(persona.telefono)
                etCorreo.setText(persona.correo)
                if(persona.genero == "f") {
                    rbFemenino.isChecked = true
                } else {
                    rbMasculino.isChecked = true
                }
            }

        } else {
            edicion = false
            title = "Nueva persona"
        }

        btnAceptar.setOnClickListener(this)
        btnCancelar.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.btnAceptar -> aceptar()
            R.id.btnCancelar -> cancelar()
        }
    }

    private fun aceptar() {
        if (etNombre.text.toString() == "" || etCorreo.text.toString() == "" || etTelefono.text.toString() == "") {
            Toast.makeText(applicationContext, "Hay campos vacios", Toast.LENGTH_LONG).show()
        } else {
            val persona = Persona()
            persona.nombre = etNombre.text.toString()
            persona.telefono = etTelefono.text.toString()
            persona.correo = etCorreo.text.toString()
            persona.genero = if(rbFemenino.isChecked) "f" else "m"
            if(edicion) {
                persona.id = id
                db.actualizarPersona(persona)
            } else
                db.adicionarPersona(persona)

            finish()
        }
    }

    private fun cancelar() {
        finish()
    }

    override fun onStop() {
        super.onStop()
        db.cerrar()
    }

}
