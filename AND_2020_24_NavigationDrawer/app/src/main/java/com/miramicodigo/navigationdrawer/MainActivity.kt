package com.miramicodigo.navigationdrawer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.miramicodigo.navigationdrawer.view.CalculadoraFragment
import com.miramicodigo.navigationdrawer.view.PerfilFragment
import com.miramicodigo.navigationdrawer.view.RegistroFragment
import kotlinx.android.synthetic.main.base_nav_draw.*

class MainActivity : AppCompatActivity() {

    lateinit var registroFragment: RegistroFragment
    lateinit var perfilFragment: PerfilFragment
    lateinit var calculadoraFragment: CalculadoraFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if(savedInstanceState != null) {
            registroFragment = supportFragmentManager.getFragment(savedInstanceState, "registroFragment") as RegistroFragment
            perfilFragment = supportFragmentManager.getFragment(savedInstanceState, "perfilFragment") as PerfilFragment
            calculadoraFragment = supportFragmentManager.getFragment(savedInstanceState, "calculadoraFragment") as CalculadoraFragment
        } else {
            registroFragment = RegistroFragment()
            perfilFragment = PerfilFragment()
            calculadoraFragment = CalculadoraFragment()
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Click en FAB", Snackbar.LENGTH_LONG).show()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        supportFragmentManager.putFragment(outState, "registroFragment", registroFragment)
        supportFragmentManager.putFragment(outState, "perfilFragment", perfilFragment)
        supportFragmentManager.putFragment(outState, "calculadoraFragment", calculadoraFragment)
    }

}
