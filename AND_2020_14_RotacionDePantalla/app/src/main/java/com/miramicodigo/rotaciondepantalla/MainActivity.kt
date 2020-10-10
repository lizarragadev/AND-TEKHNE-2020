package com.miramicodigo.rotaciondepantalla

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    // al rotar la pantalla se llama a esta funcion
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("nombres", etNombres.text.toString())
        outState.putString("apellidos", etApellidos.text.toString())
    }

    // y al volver a rotar de llama a esta funcion
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        etNombres.setText(savedInstanceState.getString("nombres", ""))
        etApellidos.setText(savedInstanceState.getString("apellidos", ""))
    }

}