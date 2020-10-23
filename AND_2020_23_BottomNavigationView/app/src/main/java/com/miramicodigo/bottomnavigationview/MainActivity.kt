package com.miramicodigo.bottomnavigationview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miramicodigo.bottomnavigationview.view.CalculadoraFragment
import com.miramicodigo.bottomnavigationview.view.PerfilFragment
import com.miramicodigo.bottomnavigationview.view.RegistroFragment

class MainActivity : AppCompatActivity() {

    lateinit var registroFragment: RegistroFragment
    lateinit var perfilFragment: PerfilFragment
    lateinit var calculadoraFragment: CalculadoraFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState != null) {
            registroFragment = supportFragmentManager.getFragment(savedInstanceState, "registroFragment") as RegistroFragment
            perfilFragment = supportFragmentManager.getFragment(savedInstanceState, "perfilFragment") as PerfilFragment
            calculadoraFragment = supportFragmentManager.getFragment(savedInstanceState, "calculadoraFragment") as CalculadoraFragment
        } else {
            registroFragment = RegistroFragment()
            perfilFragment = PerfilFragment()
            calculadoraFragment = CalculadoraFragment()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        supportFragmentManager.putFragment(outState, "registroFragment", registroFragment)
        supportFragmentManager.putFragment(outState, "perfilFragment", perfilFragment)
        supportFragmentManager.putFragment(outState, "calculadoraFragment", calculadoraFragment)
    }
}