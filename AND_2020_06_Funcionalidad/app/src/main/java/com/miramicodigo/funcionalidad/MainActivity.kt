package com.miramicodigo.funcionalidad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // R.java
        // @tipoRecurso/nombreRecurso  (Llamar un recurso desde XML)
        // R.tipoRecurso.nombreRecurso  (Llamar un recurso desde Kotlin)

        // val   (crea variables inmutables)  ******        [ ]
        // var   (crea variables mutables)                  [ ][ ]

        btnSuma.setOnClickListener {
            if(valida()) {
                //val caja1 = etNumber1.text.toString().toInt()
                //val caja2 = etNumber2.text.toString().toInt()
                //val resultado = caja1.plus(caja2)
                //tvResultado.text = "La suma es: \n$resultado"
                opera(1)
            } else {
                printError(1)
            }
        }

        btnResta.setOnClickListener {
            if(valida()) {
                //val resultado = etNumber1.text.toString().toInt().minus(etNumber2.text.toString().toInt())
                //tvResultado.text = "La resta es: \n$resultado"
                opera(2)
            }
            else {
                printError(1)
            }
        }

        btnMultiplica.setOnClickListener {
            if(valida()) {
                //tvResultado.text = "La multiplicación es: \n${etNumber1.text.toString().toInt().times(etNumber2.text.toString().toInt())}"
                opera(3)
            }
            else {
                printError(1)
                //Log.e("MainActivity", "Existen campos vacios")
                //print("Existen campos vacios")
            }
        }

        btnDivide.setOnClickListener {
            if(valida()) {
                if(etNumber2.text.toString().toInt() != 0) {
                    //tvResultado.text = "La división es: \n${etNumber1.text.toString().toDouble().div(etNumber2.text.toString().toDouble())}"
                    opera(4)
                } else {
                    printError(2)
                }
            }
            else {
                printError(1)
            }
        }

    }

    private fun valida() = etNumber1.text.toString() != "" && etNumber2.text.toString() != ""

    private fun printError(tipo: Int) {
        if(tipo == 1)
            Toast.makeText(this, "Existen campos vacíos", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(this, "No se puede realizar la división entre cero", Toast.LENGTH_LONG).show()
    }

    private fun opera(op: Int) {
        when(op) {
            1 -> tvResultado.text = "La suma es: ${etNumber1.text.toString().toInt().plus(etNumber2.text.toString().toInt())}"
            2 -> tvResultado.text = "La resta es: ${etNumber1.text.toString().toInt().minus(etNumber2.text.toString().toInt())}"
            3 -> tvResultado.text = "La multiplicación es: ${etNumber1.text.toString().toInt().times(etNumber2.text.toString().toInt())}"
            4 -> tvResultado.text = "La división es: ${etNumber1.text.toString().toDouble().div(etNumber2.text.toString().toDouble())}"
        }

        /*when {
            op > 0 -> {  }
            op == 100 -> ""
            op.div(10) == 2 -> ""
        }*/
    }

}