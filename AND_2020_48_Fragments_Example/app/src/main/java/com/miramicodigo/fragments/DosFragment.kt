package com.miramicodigo.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_dos.*

class DosFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view : View = inflater.inflate(R.layout.fragment_dos, container, false)

        val tvRes = view.findViewById<TextView>(R.id.tvResultadoSharedPreferences)

        val sharedPreferences = context?.getSharedPreferences("mis_preferencias", Context.MODE_PRIVATE)

        tvRes.text = sharedPreferences?.getString("valor1", "")

        return view
    }

    fun llegaDato(cadena : String) {
        tvResultado.text = cadena
    }

}