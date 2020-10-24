package com.miramicodigo.bottomnavigationview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.miramicodigo.bottomnavigationview.view.CalculadoraFragment
import com.miramicodigo.bottomnavigationview.view.PerfilFragment
import com.miramicodigo.bottomnavigationview.view.RegistroFragment
import kotlinx.android.synthetic.main.activity_main.*

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

        showFragment(registroFragment)

        bnvMenu.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.menuRegistro -> showFragment(registroFragment)
                R.id.menuCalculadora -> showFragment(calculadoraFragment)
                R.id.menuPerfil -> showFragment(perfilFragment)
                else -> print("")
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    fun showFragment(frag: Fragment) {
        val fm = supportFragmentManager
        val trans = fm.beginTransaction()
        trans.replace(R.id.frameContent, frag).commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        supportFragmentManager.putFragment(outState, "registroFragment", registroFragment)
        supportFragmentManager.putFragment(outState, "perfilFragment", perfilFragment)
        supportFragmentManager.putFragment(outState, "calculadoraFragment", calculadoraFragment)
    }
}