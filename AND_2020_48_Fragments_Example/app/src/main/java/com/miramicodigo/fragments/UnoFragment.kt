package com.miramicodigo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class UnoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view : View = inflater.inflate(R.layout.fragment_uno, container, false)

        val etDato : EditText = view.findViewById(R.id.etTextoDesdeFragment)
        val btnEnviar : Button = view.findViewById(R.id.btnEnviarDesdeFragment)

        btnEnviar.setOnClickListener {
            (activity as ComunicacionInterface).enviarDatoADosFragment(etDato.text.toString())
        }

        return view
    }
}