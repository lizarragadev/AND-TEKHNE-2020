package com.miramicodigo.navigationdrawer

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.miramicodigo.navigationdrawer.view.CalculadoraFragment
import com.miramicodigo.navigationdrawer.view.PerfilFragment
import com.miramicodigo.navigationdrawer.view.RegistroFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.base_nav_draw.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var registroFragment: RegistroFragment
    lateinit var perfilFragment: PerfilFragment
    lateinit var calculadoraFragment: CalculadoraFragment

    lateinit var fm: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fm = supportFragmentManager

        if(savedInstanceState != null) {
            registroFragment = fm.getFragment(savedInstanceState, "registroFragment") as RegistroFragment
            perfilFragment = fm.getFragment(savedInstanceState, "perfilFragment") as PerfilFragment
            calculadoraFragment = fm.getFragment(savedInstanceState, "calculadoraFragment") as CalculadoraFragment
        } else {
            registroFragment = RegistroFragment()
            perfilFragment = PerfilFragment()
            calculadoraFragment = CalculadoraFragment()
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Click en FAB", Snackbar.LENGTH_LONG).show()
        }

        val toggle = ActionBarDrawerToggle(this, drawerLayoutPrincipal, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayoutPrincipal.addDrawerListener(toggle)
        toggle.syncState()

        showFragment(registroFragment)

        navView.setNavigationItemSelectedListener(this)
        navView.menu.getItem(0).isChecked = true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        fm.putFragment(outState, "registroFragment", registroFragment)
        fm.putFragment(outState, "perfilFragment", perfilFragment)
        fm.putFragment(outState, "calculadoraFragment", calculadoraFragment)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.navRegistro -> showFragment(registroFragment)
            R.id.navCalculadora -> showFragment(calculadoraFragment)
            R.id.navPerfil -> showFragment(perfilFragment)
            else -> print("")
        }
        drawerLayoutPrincipal.closeDrawer(GravityCompat.START)
        return true
    }

    private fun showFragment(frag: Fragment) {
        fm.beginTransaction().replace(R.id.frameContent, frag).commit()
    }

}
