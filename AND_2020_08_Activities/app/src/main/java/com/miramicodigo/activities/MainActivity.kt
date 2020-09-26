package com.miramicodigo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("MainActivity", "====== Entro a OnCreate() ======")

        btnIrASegunda.setOnClickListener {
            val intent = Intent(this, SegundaActivity::class.java)
            startActivity(intent)
        }

        btnIrATercera.setOnClickListener {
            startActivity(Intent(this, TerceraActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e("MainActivity", "====== Entro a OnStart() ======")
    }

    override fun onResume() {
        super.onResume()
        Log.e("MainActivity", "====== Entro a OnResume() ======")
    }

    override fun onPause() {
        super.onPause()
        Log.e("MainActivity", "====== Entro a OnPause() ======")
    }

    override fun onStop() {
        super.onStop()
        Log.e("MainActivity", "====== Entro a OnStop() ======")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("MainActivity", "====== Entro a OnDestroy() ======")
    }
}