package com.miramicodigo.restful_3.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.miramicodigo.restful_3.R
import com.miramicodigo.restful_3.SessionPrefs
import com.miramicodigo.restful_3.model.ResponsePersonaLogin
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    lateinit var responsePersonaLogin: ResponsePersonaLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(!SessionPrefs(this).get(this).estaLogueado()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_main)

        responsePersonaLogin = SessionPrefs(this).get(this).getPersona()
        tvUsuario.text = responsePersonaLogin.id
        tvNombre.text = responsePersonaLogin.name
        tvDireccion.text = responsePersonaLogin.address
        tvGenero.text = if(responsePersonaLogin.gender == "M") "Masculino" else "Fememnino"

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuSalir -> confirmaCerrarSesion().show()
        }
        return super.onOptionsItemSelected(item)
    }

    fun confirmaCerrarSesion(): AlertDialog {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("CERRAR SESIÓN")
                .setMessage("¿Estás seguro que deseas cerrar sesión?")
                .setCancelable(false)
                .setPositiveButton("Si") { dInt, i ->
                    SessionPrefs(this).get(this).cerrarSesion()
                    finish()
                    startActivity(Intent(this, LoginActivity::class.java))
                    Toast.makeText(this, "Se cerró la sesión exitosamente", Toast.LENGTH_SHORT).show()
                }
                .setNeutralButton("Cancelar") { dInt, i ->
                }

        return alertDialog.create()
    }

}