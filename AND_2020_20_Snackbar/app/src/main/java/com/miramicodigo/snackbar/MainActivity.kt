package com.miramicodigo.snackbar

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSnackbarSimple.setOnClickListener {
            Snackbar.make(coordinatorLayout, "Snackbar Simple", Snackbar.LENGTH_SHORT).show()
        }

        btnSnackbarConAccion.setOnClickListener {
            val snackbar = Snackbar.make(coordinatorLayout, "Snackbar con acción", Snackbar.LENGTH_SHORT)
            snackbar.setAction("Reintentar") {
                Toast.makeText(applicationContext, "Click en accion de Snackbar", Toast.LENGTH_SHORT).show()
            }
            snackbar.show()
        }

        btnSnackbarPersonalizado.setOnClickListener {
            val snackbar = Snackbar.make(coordinatorLayout, "Snackbar personalizado", Snackbar.LENGTH_SHORT)
            snackbar.setAction("Deshacer") {
                Toast.makeText(applicationContext, "Click en accion de Snackbar", Toast.LENGTH_SHORT).show()
            }
            snackbar.setActionTextColor(Color.parseColor("#E040FB"))
            snackbar.setTextColor(Color.parseColor("#FFFFFF"))
            snackbar.setBackgroundTint(Color.parseColor("#4CAF50"))
            snackbar.show()
        }
    }
}