package com.miramicodigo.archivos

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), View.OnClickListener {

    val nombreArchivoInterno = "prueba_archivo_int.txt"
    val nombreArchivoExterno = "prueba_archivo_ext.txt"
    val nombreCarpeta = "/TEKHNE/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnInternoLeer.setOnClickListener(this)
        btnInternoGuardar.setOnClickListener(this)
        btnInternoBorrar.setOnClickListener(this)
        btnExternoLeer.setOnClickListener(this)
        btnExternoGuardar.setOnClickListener(this)
        btnExternoBorrar.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.btnInternoGuardar -> guardarInterno()
            R.id.btnInternoLeer -> leerInterno()
            R.id.btnInternoBorrar -> borrarInterno()
            R.id.btnExternoGuardar -> verificaPermiso()
            R.id.btnExternoLeer -> leerExterno()
            R.id.btnExternoBorrar -> borrarExterno()
        }
    }

    fun guardarInterno() {
        if (etInterno.text.toString() != "") {
            try {



            } catch (e: Exception) {
                println("Error: " + e.message)
            }
        } else {
            Toast.makeText(applicationContext, "Debe ingresar datos para guardar", Toast.LENGTH_SHORT).show()
        }
    }

    fun leerInterno() {
        try {



        } catch (e: Exception) {
            println("Error: " + e.message)
        }
    }

    fun borrarInterno() {



    }

    fun guardarExterno() {
        if (etExterno.text.toString() != "") {
            var sdDisponible: Boolean
            var sdAccesoEscritura: Boolean
            val state = Environment.getExternalStorageState()
            if (Environment.MEDIA_MOUNTED == state) {
                sdDisponible = true
                sdAccesoEscritura = true
            } else {
                if (Environment.MEDIA_MOUNTED_READ_ONLY == state) {
                    sdDisponible = true
                    sdAccesoEscritura = false
                } else {
                    sdDisponible = false
                    sdAccesoEscritura = false
                }
            }
            if (sdDisponible && sdAccesoEscritura) {
                try {



                } catch (ioe: IOException) {
                    Toast.makeText(this, "No se pudo grabar", Toast.LENGTH_SHORT).show()
                }

            } else {
                println("No se puede escribir en su memoria")
            }
        } else {
            Toast.makeText(applicationContext, "Debe ingresar datos para guardar", Toast.LENGTH_SHORT).show()
        }

    }

    fun leerExterno() {
        try {



        } catch (e: Exception) {
            println("Error: " + e.message)
        }
    }

    fun borrarExterno() {



    }

    fun verificaPermiso() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(this)) {
                requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE), 1)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("Permiso", "Concedido")
                    guardarExterno()
                } else {
                    Toast.makeText(applicationContext, "Se denegó el permiso de escritura", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

}
