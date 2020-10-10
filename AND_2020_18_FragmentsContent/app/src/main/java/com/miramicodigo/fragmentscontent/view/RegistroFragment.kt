package com.miramicodigo.fragmentscontent.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.miramicodigo.fragmentscontent.R
import kotlinx.android.synthetic.main.fragment_registro.view.*

class RegistroFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_registro, container, false)

        v.btnCancelar.setOnClickListener {
            Toast.makeText(context, "Accion click", Toast.LENGTH_LONG).show()
        }

        v.btnAceptar.setOnClickListener {
            if(v.etNombres.text.toString() == "" || v.etApellidos.text.toString() == "") {
                Toast.makeText(context, "Existen campos vacios", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Listo para enviar", Toast.LENGTH_LONG).show()
            }
        }
        return v
    }

}