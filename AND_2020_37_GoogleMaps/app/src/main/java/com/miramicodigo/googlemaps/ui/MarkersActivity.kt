package com.miramicodigo.googlemaps.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.miramicodigo.googlemaps.R


class MarkersActivity : AppCompatActivity() , OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_markers)

        title = "Markers"

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapMarkers) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val coord = resources.getStringArray(R.array.coord_bol)
        val listaCoord = ArrayList<LatLng>()
        for(i in coord.indices) {
            val convertVect = (coord[i].split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
            listaCoord.add(LatLng(convertVect[0].toDouble(), convertVect[1].toDouble()))
        }

        for (i in listaCoord.indices) {
            val m = MarkerOptions()
                    .position(listaCoord[i])
                    .title("Marcador en posicion ${i+1}")
            googleMap.addMarker(m)
        }

        val centroMarcador = LatLng(-16.501514, -68.132799)
        val markOpt = MarkerOptions()
                .position(centroMarcador)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
                .title("Dumbo")
                .draggable(true)

        googleMap.addMarker(markOpt)
        googleMap.setOnMarkerClickListener(this)

        val zoom = CameraUpdateFactory.zoomTo(5f)
        val centroMapa = CameraUpdateFactory.newLatLng(LatLng(-17.110985, -64.239348))

        googleMap.moveCamera(centroMapa)
        googleMap.animateCamera(zoom)

    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        Toast.makeText(this, "${p0?.title} - ${p0?.position?.latitude} - ${p0?.position?.longitude}",
                Toast.LENGTH_SHORT).show()
        return true
    }

}
