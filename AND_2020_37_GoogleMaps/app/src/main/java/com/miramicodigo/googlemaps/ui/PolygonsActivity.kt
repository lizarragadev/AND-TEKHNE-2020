package com.miramicodigo.googlemaps.ui

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolygonOptions
import com.miramicodigo.googlemaps.R

class PolygonsActivity : AppCompatActivity(), OnMapReadyCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_polygons)

        title = "Polygons"

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapPolygon) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        val coord = resources.getStringArray(R.array.coord_around_bol)
        val listaCoord = ArrayList<LatLng>()
        for(i in coord.indices) {
            val convertVect = (coord[i].split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
            listaCoord.add(LatLng(convertVect[0].toDouble(), convertVect[1].toDouble()))
        }
        val boliviaPolygon = PolygonOptions()
        boliviaPolygon.addAll(listaCoord)
        boliviaPolygon.strokeColor(Color.BLUE)
        boliviaPolygon.fillColor(Color.CYAN)

        googleMap?.addPolygon(boliviaPolygon)

        val zoom = CameraUpdateFactory.zoomTo(5f)
        val centroMapa = CameraUpdateFactory.newLatLng(LatLng(-17.110985, -64.239348))
        googleMap?.moveCamera(centroMapa)
        googleMap?.animateCamera(zoom)
    }

}
