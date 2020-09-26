package com.miramicodigo.intents

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val DEVUELVE_DATOS = 555

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ibAbrirActivity.setOnClickListener {
            abrirActivity()
        }
        ibEnviarDatos.setOnClickListener {
            enviarDatos()
        }
        ibDevolverDatos.setOnClickListener {
            devolverDatos()
        }

    }

    private fun abrirActivity() {
        startActivity(Intent(this, SegundaActivity::class.java))
    }

    private fun enviarDatos() {
        val intent = Intent(this, SegundaActivity::class.java)
        intent.putExtra("valor1", "Envio de dato")
        intent.putExtra("valor2", 222)
        startActivity(intent)
    }

    private fun devolverDatos() {
        val intent = Intent(this, SegundaActivity::class.java)
        intent.putExtra("valor3", "Mi nombre es ")
        startActivityForResult(intent, DEVUELVE_DATOS)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == DEVUELVE_DATOS) {
            if(resultCode == Activity.RESULT_OK) {
                val resultado = data?.getStringExtra("respuesta").toString()
                Toast.makeText(this, "La respuesta es: $resultado", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Se canceló la respuesta", Toast.LENGTH_LONG).show()
            }
        }
    }



}