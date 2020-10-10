package com.miramicodigo.fragmentssimple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ibBolivia.setOnClickListener(this)
        ibParaguay.setOnClickListener(this)
        ibBrasil.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            ibBolivia.id -> showFragment(BoliviaFragment())
            ibParaguay.id -> showFragment(ParaguayFragment())
            ibBrasil.id -> showFragment(BrasilFragment())
        }
    }

    fun showFragment(frag: Fragment) {
        val fragManager = supportFragmentManager
        val fragTransaction = fragManager.beginTransaction()
        fragTransaction.replace(R.id.frameContent, frag).commit()
    }

}