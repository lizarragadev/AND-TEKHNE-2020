package com.miramicodigo.googlemaps.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.miramicodigo.googlemaps.R

class CustomMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_maps)

        title = "Custom Maps"

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapCustom) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_blue))

        val zoom = CameraUpdateFactory.zoomTo(5f)
        val centroMapa = CameraUpdateFactory.newLatLng(LatLng(-17.110985, -64.239348))

        googleMap.moveCamera(centroMapa)
        googleMap.animateCamera(zoom)
    }

}
