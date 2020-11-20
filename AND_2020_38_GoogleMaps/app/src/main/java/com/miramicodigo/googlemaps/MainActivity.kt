package com.miramicodigo.googlemaps

import android.os.Bundle
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.miramicodigo.googlemaps.ui.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPolylines.setOnClickListener(this)
        btnPolygons.setOnClickListener(this)
        btnCircle.setOnClickListener(this)
        btnMarkers.setOnClickListener(this)
        btnEvents.setOnClickListener(this)
        btnCustomMap.setOnClickListener(this)
    }

    override fun onClick(p0: View) {
        when(p0.id) {
            R.id.btnPolylines -> startActivity(Intent(applicationContext, PolylinesActivity::class.java))
            R.id.btnPolygons -> startActivity(Intent(applicationContext, PolygonsActivity::class.java))
            R.id.btnCircle -> startActivity(Intent(applicationContext, CircleActivity::class.java))
            R.id.btnMarkers -> startActivity(Intent(applicationContext, MarkersActivity::class.java))
            R.id.btnEvents -> startActivity(Intent(applicationContext, EventsActivity::class.java))
            R.id.btnCustomMap -> startActivity(Intent(applicationContext, CustomMapsActivity::class.java))
        }
    }


}
