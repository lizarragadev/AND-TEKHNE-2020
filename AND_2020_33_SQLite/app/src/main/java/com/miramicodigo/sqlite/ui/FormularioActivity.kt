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

        }
    }

    private fun cancelar() {

    }

    override fun onStop() {
        super.onStop()

    }

}
