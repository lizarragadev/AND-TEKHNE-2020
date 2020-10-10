package com.miramicodigo.fragmentscontent

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.miramicodigo.fragmentscontent.view.CalculadoraFragment
import com.miramicodigo.fragmentscontent.view.PerfilFragment
import com.miramicodigo.fragmentscontent.view.RegistroFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var registroFragment: RegistroFragment
    lateinit var perfilFragment: PerfilFragment
    lateinit var calculadoraFragment: CalculadoraFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        if(savedInstanceState != null) {
            registroFragment = supportFragmentManager.getFragment(savedInstanceState, "registroFragment") as RegistroFragment
            perfilFragment = supportFragmentManager.getFragment(savedInstanceState, "perfilFragment") as PerfilFragment
            calculadoraFragment = supportFragmentManager.getFragment(savedInstanceState, "calculadoraFragment") as CalculadoraFragment
        } else {
            registroFragment = RegistroFragment()
            perfilFragment = PerfilFragment()
            calculadoraFragment = CalculadoraFragment()
        }

        ibRegistro.setOnClickListener(this)
        ibPerfil.setOnClickListener(this)
        ibCalculadora.setOnClickListener(this)

        showFragment(registroFragment)
        ibRegistro.setBackgroundColor(Color.RED)
        ibPerfil.setBackgroundColor(Color.GRAY)
        ibCalculadora.setBackgroundColor(Color.GRAY)
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            ibRegistro.id -> {
                showFragment(registroFragment)
                ibRegistro.setBackgroundColor(Color.RED)
                ibPerfil.setBackgroundColor(Color.GRAY)
                ibCalculadora.setBackgroundColor(Color.GRAY)
            }
            ibPerfil.id -> {
                showFragment(perfilFragment)
                ibRegistro.setBackgroundColor(Color.GRAY)
                ibPerfil.setBackgroundColor(Color.RED)
                ibCalculadora.setBackgroundColor(Color.GRAY)
            }
            ibCalculadora.id -> {
                showFragment(calculadoraFragment)
                ibRegistro.setBackgroundColor(Color.GRAY)
                ibPerfil.setBackgroundColor(Color.GRAY)
                ibCalculadora.setBackgroundColor(Color.RED)
            }
        }
    }

    fun showFragment(frag: Fragment) {
        val fragManager = supportFragmentManager
        val fragTransaction = fragManager.beginTransaction()
        fragTransaction.replace(R.id.frameContent, frag).commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        supportFragmentManager.putFragment(outState, "registroFragment", registroFragment)
        supportFragmentManager.putFragment(outState, "perfilFragment", perfilFragment)
        supportFragmentManager.putFragment(outState, "calculadoraFragment", calculadoraFragment)
    }

}