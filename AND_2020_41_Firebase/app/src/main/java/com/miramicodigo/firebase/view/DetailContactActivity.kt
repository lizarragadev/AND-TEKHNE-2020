package com.miramicodigo.firebase.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.miramicodigo.firebase.R
import com.miramicodigo.firebase.model.Contact
import kotlinx.android.synthetic.main.activity_detail_contact.*

class DetailContactActivity : AppCompatActivity() {

    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_contact)

        db = FirebaseFirestore.getInstance()

        val contacto = intent.getSerializableExtra("contact") as Contact
        mostrarTiempoReal(contacto)
    }

    private fun mostrarTiempoReal(con: Contact) {
        val contactListener: DocumentReference = db.collection(COLLECTION_CONTACTO).document(con.contactosId.toString())
        contactListener.addSnapshotListener { value, error ->
            if(value != null && value.exists()) {
                val obj = value.toObject(Contact::class.java)
                tvDetailNombre.text = obj?.nombre
                tvDetailEmail.text = obj?.correo
                tvDetailTelefono.text = obj?.telefono
            }
        }
    }

    companion object {
        private const val NOMBRE_KEY = "nombre"
        private const val CORREO_KEY = "correo"
        private const val TELEFONO_KEY = "telefono"

        private const val COLLECTION_CONTACTO = "Contactos"
    }

}