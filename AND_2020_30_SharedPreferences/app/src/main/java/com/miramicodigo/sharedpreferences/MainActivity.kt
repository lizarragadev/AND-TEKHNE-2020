package com.miramicodigo.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnGuardarUno.setOnClickListener(this)
        btnGuardarDos.setOnClickListener(this)
        btnLeerUno.setOnClickListener(this)
        btnLeerDos.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnGuardarUno -> guardarValor(etTextoUno.text.toString(), "valor1", etTextoUno)
            R.id.btnGuardarDos -> guardarValor(etTextoDos.text.toString(), "valor2", etTextoDos)
            R.id.btnLeerUno -> etTextoUno.setText(leerValor("valor1"))
            R.id.btnLeerDos -> etTextoDos.setText(leerValor("valor2"))
        }
    }

    fun guardarValor(texto: String, nombre: String, et: EditText) {

    }

    fun leerValor(nombre: String): String {
        return ""
    }
}
