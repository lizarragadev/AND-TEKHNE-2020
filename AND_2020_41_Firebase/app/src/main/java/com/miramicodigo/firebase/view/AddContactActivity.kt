package com.miramicodigo.firebase.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.miramicodigo.firebase.R
import kotlinx.android.synthetic.main.activity_add_contact.*
import java.util.*
import kotlin.collections.HashMap

class AddContactActivity : AppCompatActivity() {

    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        db = FirebaseFirestore.getInstance()

        btnGuardar.setOnClickListener {
            agregarNuevoContacto()
        }
    }

    private fun agregarNuevoContacto() {
        val contacto: MutableMap<String, Any> = HashMap()
        contacto[NOMBRE_KEY] = etNombre.text.toString()
        contacto[CORREO_KEY] = etEmail.text.toString()
        contacto[TELEFONO_KEY] = etTelefono.text.toString()

        db.collection(COLLECTION_CONTACTO).document(UUID.randomUUID().toString()).set(contacto)
                .addOnSuccessListener {
                    Toast.makeText(this, "Contactto registrado existosamente", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener {  err ->
                    Log.e("AddContactActivity", err.toString())
                }
    }


    companion object {
        private const val NOMBRE_KEY = "nombre"
        private const val CORREO_KEY = "correo"
        private const val TELEFONO_KEY = "telefono"

        private const val COLLECTION_CONTACTO = "Contactos"
    }
}