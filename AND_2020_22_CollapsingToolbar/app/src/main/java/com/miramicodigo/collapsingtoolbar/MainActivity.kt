package com.miramicodigo.collapsingtoolbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(miToolbar)

        collapsingTL.title = "¡Oh linda La Paz!"

        fabAnd.setOnClickListener {
            Toast.makeText(this, "Click en FAB", Toast.LENGTH_SHORT).show()
        }

    }
}