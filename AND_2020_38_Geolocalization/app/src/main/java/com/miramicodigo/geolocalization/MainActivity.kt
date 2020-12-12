package com.miramicodigo.geolocalization

import android.Manifest
import android.content.Context
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {
    val MY_PERMISSIONS_REQUEST_LOCATION = 100

    lateinit var mMap: GoogleMap
    lateinit var mGoogleApliClient: GoogleApiClient
    lateinit var mLocationRequest: LocationRequest
    lateinit var mLocationCallback: LocationCallback
    lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        mGoogleApliClient = GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build()

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10000)
                .setFastestInterval(1000)
                .setSmallestDisplacement(100f)

        mLocationCallback = object: LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                val locat = locationResult?.locations?.get(0)
                val centroMapa = CameraUpdateFactory.newLatLngZoom(locat?.latitude?.let { LatLng(it, locat.longitude) }, 19f)
                mMap.animateCamera(centroMapa)

                tvLatitud.text = locat?.latitude.toString()
                tvLongitud.text = locat?.longitude.toString()
            }
        }

    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        if(connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this, 9000)
            } catch (e: IntentSender.SendIntentException) {
                e.printStackTrace()
            }
        }
    }

    override fun onConnected(p0: Bundle?) {
        if(checkLocationPermission()) {
            mMap.isMyLocationEnabled = true
            fusedLocationClient.lastLocation
                    .addOnSuccessListener(this) { location ->
                        if(location != null) {
                            fusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.getMainLooper())
                        } else {
                            location?.let { handleNewLocation(it) }
                        }
                    }
        }
    }

    private fun handleNewLocation(location: Location) {
        val currentLatitude = location.latitude
        val currentLongitude = location.longitude
        val latLng = LatLng(currentLatitude, currentLongitude)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
    }

    override fun onConnectionSuspended(i: Int) {

    }

    override fun onLocationChanged(p0: Location?) {
        p0?.let { handleNewLocation(it) }
    }

    override fun onResume() {
        super.onResume()
        mGoogleApliClient.connect()
    }

    override fun onPause() {
        super.onPause()
        if(mGoogleApliClient.isConnected) {
            fusedLocationClient.removeLocationUpdates(mLocationCallback)
            mGoogleApliClient.disconnect()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val centroMapa = CameraUpdateFactory.newLatLngZoom(LatLng(-16.981728, -64.041366), 5.5f)
        mMap.moveCamera(centroMapa)
    }

    fun checkLocationPermission(): Boolean {
        return if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), MY_PERMISSIONS_REQUEST_LOCATION)
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), MY_PERMISSIONS_REQUEST_LOCATION)
            }
            false
        } else {
            true
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_LOCATION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

}
