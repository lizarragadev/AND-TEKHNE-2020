package com.miramicodigo.pedidos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var cantidad = 1
    val precio = 2.5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCantidad.text = cantidad.toString()
    }

    fun menos(v: View) {
        if(cantidad > 1)
            cantidad -= 1
        tvCantidad.text = cantidad.toString()
    }

    fun mas(v: View) {
        cantidad += 1
        tvCantidad.text = cantidad.toString()
    }

    fun calcular(v: View) {
        if(etNombre.text.toString() != "") {
            tvResumenCantidad.text = cantidad.toString()
            tvResumenPrecioUnitario.text = "Bs. $precio"
            tvResumenCostoTotal.text = "Bs. ${cantidad * precio}"
            tvResumenNombre.text = etNombre.text.toString()
        } else {
            Toast.makeText(this, "Escriba un nombre", Toast.LENGTH_SHORT).show()
        }
    }
}