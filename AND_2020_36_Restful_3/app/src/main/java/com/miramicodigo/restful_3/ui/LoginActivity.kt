package com.miramicodigo.restful_3.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import kotlinx.android.synthetic.main.activity_login.*
import android.net.ConnectivityManager
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.miramicodigo.restful_3.R
import com.miramicodigo.restful_3.SessionPrefs
import com.miramicodigo.restful_3.model.ApiError
import com.miramicodigo.restful_3.model.LoginBody
import com.miramicodigo.restful_3.model.ResponsePersonaLogin
import com.miramicodigo.restful_3.service.PersonaService

@Suppress("DEPRECATION")
class LoginActivity : AppCompatActivity() {

    lateinit var retrofit: Retrofit
    lateinit var personaService: PersonaService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


    }

    private fun login() {
        val user = etUsuario.text.toString()
        val password = etPassword.text.toString()

        tilPassword.error = null
        tilUsuario.error = null

        var cancel = false
        lateinit var focusView: View

        if (TextUtils.isEmpty(password)) {
            tilPassword.error = getString(R.string.error_field_required)
            focusView = tilPassword
            cancel = true
        } else if (!esPasswordValido(password)) {
            tilPassword.error = getString(R.string.error_invalid_password)
            focusView = tilPassword
            cancel = true
        }
        if (TextUtils.isEmpty(user)) {
            tilUsuario.error = getString(R.string.error_field_required)
            focusView = tilUsuario
            cancel = true
        } else if (!esUsuarioValido(user)) {
            tilUsuario.error = getString(R.string.error_invalid_user_id)
            focusView = tilUsuario
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
        pbProgreso.visibility = if (show) View.VISIBLE else View.GONE
        viewContent.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun irPantallaLogueado(responsePersonaLogin: ResponsePersonaLogin?) {
        val intent = Intent(this, MainActivity::class.java)
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
