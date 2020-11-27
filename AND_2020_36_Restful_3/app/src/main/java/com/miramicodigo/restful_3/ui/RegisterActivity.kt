package com.miramicodigo.restful_3.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.miramicodigo.restful_3.R
import com.miramicodigo.restful_3.service.PersonaService
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Retrofit

@Suppress("DEPRECATION")
class RegisterActivity : AppCompatActivity() {

    lateinit var retrofit: Retrofit
    lateinit var personaService: PersonaService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)



    }

    private fun register() {
        val user = etRegisterUsuario.text.toString()
        val password = etRegisterPassword.text.toString()
        val repeatPassword = etRegisterRepeatPassword.text.toString()
        val name = etRegisterNombre.text.toString()
        val address = etRegisterAddress.text.toString()
        val male = rbRegisterMale.isChecked

        tilRegisterUsuario.error = null
        tilRegisterPassword.error = null
        tilRegisterRepeatPassword.error = null
        tilRegisterNombre.error = null
        tilRegisterAddress.error = null

        var cancel = false
        lateinit var focusView: View

        if (TextUtils.isEmpty(user)) {
            tilRegisterUsuario.error = getString(R.string.error_field_required)
            focusView = tilRegisterUsuario
            cancel = true
        } else if (!esUsuarioValido(user)) {
            tilRegisterUsuario.error = getString(R.string.error_invalid_user_id)
            focusView = tilRegisterUsuario
            cancel = true
        }

        if (TextUtils.isEmpty(password)) {
            tilRegisterPassword.error = getString(R.string.error_field_required)
            focusView = tilRegisterPassword
            cancel = true
        } else if (!esPasswordValido(password)) {
            tilRegisterPassword.error = getString(R.string.error_invalid_password)
            focusView = tilRegisterPassword
            cancel = true
        }

        if (TextUtils.isEmpty(repeatPassword)) {
            tilRegisterRepeatPassword.error = getString(R.string.error_field_required)
            focusView = tilRegisterRepeatPassword
            cancel = true
        } else if (!esPasswordValido(password)) {
            tilRegisterRepeatPassword.error = getString(R.string.error_invalid_password)
            focusView = tilRegisterRepeatPassword
            cancel = true
        }

        if (TextUtils.isEmpty(name)) {
            tilRegisterNombre.error = getString(R.string.error_field_required)
            focusView = tilRegisterNombre
            cancel = true
        }

        if (TextUtils.isEmpty(address)) {
            tilRegisterAddress.error = getString(R.string.error_field_required)
            focusView = tilRegisterAddress
            cancel = true
        }


        if (cancel) {
            focusView.requestFocus()
        } else {




        }
    }

    private fun esUsuarioValido(userId: String): Boolean {
        return userId.length >= 3
    }

    private fun esPasswordValido(password: String): Boolean {
        return password.length > 4
    }

    private fun mostrarProgreso(show: Boolean) {
        viewRegisterContent.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun irPantallaLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun mostrarErrorLogueo(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    private fun hayInternet(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val actNetInfo = connectivityManager.activeNetworkInfo

        return actNetInfo != null && actNetInfo.isConnected
    }

    private fun abrirConfig() {
        Toast.makeText(this, "No hay conexion a Internet", Toast.LENGTH_LONG).show()
        startActivity(Intent(Settings.ACTION_SETTINGS))
    }

}
