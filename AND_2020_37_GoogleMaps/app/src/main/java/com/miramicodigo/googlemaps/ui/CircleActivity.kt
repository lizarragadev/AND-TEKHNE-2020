package com.miramicodigo.googlemaps.ui

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.miramicodigo.googlemaps.R

class CircleActivity : AppCompatActivity(), OnMapReadyCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circle)

        title = "Circle"

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapCircle) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        val centroCirculo = LatLng(-16.500895, -68.133105)
        val radio = 20

        val opcionesCirculo = CircleOptions()
        opcionesCirculo.center(centroCirculo)
        opcionesCirculo.radius(radio.toDouble())
        opcionesCirculo.strokeColor(Color.GREEN)
        opcionesCirculo.fillColor(Color.argb(50, 33, 200, 243))
        opcionesCirculo.strokeWidth(4f)

        googleMap.addCircle(opcionesCirculo)

        val zoom = CameraUpdateFactory.zoomTo(19f)
        val centroMapa = CameraUpdateFactory.newLatLng(centroCirculo)

        googleMap.moveCamera(centroMapa)
        googleMap.animateCamera(zoom)

    }
}
