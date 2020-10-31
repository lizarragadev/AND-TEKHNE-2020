package com.miramicodigo.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    // Almacenamiento en Android
    // 1. SharedPreferences (Local)  "*.xml" (Informacion pequeña)
    // 2. Archivos (Local) ".txt" (Almacenamiento interno y Almacenamiento externo)  (Información extensa)
    // 3. Base de datos (Local) (Informacion estructurada)

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("mis_preferencias", Context.MODE_PRIVATE)
        // { clave: valor }

        if(!sharedPreferences.getBoolean("primeraVez", false)) {
            val editor = sharedPreferences.edit()
            editor.putBoolean("primeraVez", true)
            editor.apply()
            etLabelInit.text = "Es primera vez que ingresas"
        } else {
            etLabelInit.text = "Ya ingresaste mas de 1 vez"
        }

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
        val editor = sharedPreferences.edit()
        editor.putString(nombre, texto)
        editor.apply()
        et.setText("")
        Toast.makeText(this, "Se guardo existosamente", Toast.LENGTH_SHORT).show()
    }

    fun leerValor(nombre: String): String {
        return sharedPreferences.getString(nombre, "").toString()
    }
}
