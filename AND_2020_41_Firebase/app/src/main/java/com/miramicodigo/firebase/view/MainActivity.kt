package com.miramicodigo.firebase.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.miramicodigo.firebase.R
import com.miramicodigo.firebase.model.Contact
import com.miramicodigo.firebase.view.adapter.RVAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var adaptador: RecyclerView.Adapter<*>
    lateinit var datos: ArrayList<Contact>
    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = FirebaseFirestore.getInstance()

        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvContacts.layoutManager = layoutManager
    }

    private fun getContacts() {
        datos = ArrayList()
        db.collection(COLLECTION_CONTACTO)
                .get()
                .addOnSuccessListener { result ->
                    result.forEach {
                        val cont = it.toObject(Contact::class.java)
                        cont.contactosId = it.id
                        datos.add(cont)
                    }
                    adaptador = RVAdapter(this, datos)
                    rvContacts.adapter = adaptador
                }
                .addOnFailureListener {
                    Log.e("MainActivity", "Error: ${it.message}")
                }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.itemAdd -> startActivity(Intent(this, AddContactActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        getContacts()
    }

    companion object {
        private const val NOMBRE_KEY = "nombre"
        private const val CORREO_KEY = "correo"
        private const val TELEFONO_KEY = "telefono"

        private const val COLLECTION_CONTACTO = "Contactos"
    }
}