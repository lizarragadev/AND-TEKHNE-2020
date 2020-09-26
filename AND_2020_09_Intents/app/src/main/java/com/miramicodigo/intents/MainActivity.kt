package com.miramicodigo.intents

import android.Manifest
import android.app.Activity
import android.app.SearchManager
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    // extends  (herencia)  :
    // implements   (implementaciones)   :   ,

    val DEVUELVE_DATOS = 555
    val PERMISO_LLAMADA = 777

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

        ibAbrirMarcado.setOnClickListener(this)
        ibLlamar.setOnClickListener(this)
        ibAbrirGoogleMaps.setOnClickListener(this)
        ibAbrirStreetView.setOnClickListener(this)
        ibAbrirPaginaWeb.setOnClickListener(this)
        ibAbrirBuscador.setOnClickListener(this)
        ibCompartirTexto.setOnClickListener(this)
        ibCapturaImagen.setOnClickListener(this)
        ibEnviarEmail.setOnClickListener(this)
        ibAbrirApp.setOnClickListener(this)

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
        } else {
            if (requestCode == 111) {
                if(resultCode == Activity.RESULT_OK) {
                    val bitmap = data?.extras?.get("data") as Bitmap
                    ibCapturaImagen.setImageBitmap(bitmap)
                } else {
                    Toast.makeText(this, "Se cancelo", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            ibAbrirMarcado.id -> abrirMarcado()
            ibLlamar.id -> llamar()
            ibAbrirGoogleMaps.id -> abrirGoogleMaps()
            ibAbrirStreetView.id -> abrirStreetView()
            ibAbrirPaginaWeb.id -> abrirPaginaWeb()
            ibAbrirBuscador.id -> abrirBuscador()
            ibCompartirTexto.id -> compartirTexto()
            ibCapturaImagen.id -> canpturarImagen()
            ibEnviarEmail.id -> enviarEmail()
            ibAbrirApp.id -> abrirApp()
        }
    }

    private fun abrirApp() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.setClassName("com.miramicodigo.activities", "com.miramicodigo.activities.MainActivity")
        startActivity(intent)
    }

    private fun enviarEmail() {
        val TO = arrayOf("lizarraga.dev@gmail.com", "kotlinlapaz@gmail.com")
        val CC = arrayOf("luisito@outlook.com")
        val asunto = "Correo importante"
        val contenido = "Estimados amigos les envio este correo para informarles...."

        val intent = Intent(Intent.ACTION_SENDTO)
        intent.type = "text/plain"
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, TO)
        intent.putExtra(Intent.EXTRA_CC, CC)
        intent.putExtra(Intent.EXTRA_SUBJECT, asunto)
        intent.putExtra(Intent.EXTRA_TEXT, contenido)

        startActivity(intent)
    }

    private fun canpturarImagen() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePicture ->
            takePicture.resolveActivity(packageManager)?.also {
                startActivityForResult(takePicture, 111)
            }
        }
    }

    private fun compartirTexto() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "Descarga esta aplicacion del siguiente enlace: bla  bla bla")
        startActivity(intent)
    }

    private fun abrirBuscador() {
        val intent = Intent(Intent.ACTION_WEB_SEARCH)
        intent.putExtra(SearchManager.QUERY, "Android")
        startActivity(intent)
    }

    private fun abrirPaginaWeb() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://www.google.com")
        startActivity(intent)
    }

    private fun abrirStreetView() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("google.streetview:cbll=-16.508355,-68.126270")
        startActivity(intent)
    }

    private fun abrirGoogleMaps() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("geo:-16.508355, -68.126270")
        startActivity(intent)
    }

    private fun llamar() {
        val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
        if(permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), PERMISO_LLAMADA)
        } else {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:77752810")
            startActivity(intent)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode) {
            PERMISO_LLAMADA -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    llamar()
                } else {
                    Toast.makeText(this, "Permiso denegado", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun abrirMarcado() {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:77752810")
        startActivity(intent)
    }


}