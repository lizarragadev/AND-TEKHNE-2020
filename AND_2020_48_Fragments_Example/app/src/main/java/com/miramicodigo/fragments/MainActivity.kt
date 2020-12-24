package com.miramicodigo.fragments

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ComunicacionInterface{

    lateinit var fragUno : UnoFragment
    lateinit var fragDos : DosFragment
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("mis_preferencias", Context.MODE_PRIVATE);

        val editor = sharedPreferences.edit()
        editor.putString("valor1", "Texto guardado en preferences")
        editor.apply()

        fragUno = UnoFragment()
        fragDos = DosFragment()

        showFragment(fragUno, R.id.frameUno)
        showFragment(fragDos, R.id.frameDos)

        btnEnviarDesdeActivity.setOnClickListener {
            fragDos.llegaDato(etTextoDesdeActivity.text.toString())
        }
    }

    fun showFragment(fragment: Fragment, id: Int) {
        val fragManager = supportFragmentManager
        var fragTransaction = fragManager.beginTransaction()
        fragTransaction.add(id, fragment).commit()
    }

    override fun enviarDatoADosFragment(cadena: String) {
        (fragDos).llegaDato(cadena)
    }

}