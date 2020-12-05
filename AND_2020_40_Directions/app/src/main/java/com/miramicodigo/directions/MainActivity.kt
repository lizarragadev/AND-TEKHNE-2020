package com.miramicodigo.directions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.DirectionsApi
import com.google.maps.GeoApiContext
import com.google.maps.PendingResult
import com.google.maps.android.PolyUtil
import com.google.maps.errors.ApiException
import com.google.maps.model.DirectionsResult
import com.google.maps.model.LatLng
import com.google.maps.model.TravelMode
import kotlinx.android.synthetic.main.activity_main.*
import org.joda.time.DateTime
import java.io.IOException
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    lateinit var googleMap: GoogleMap
    val inicioLatLng = LatLng(-16.504413, -68.131360)
    val finLatLng = LatLng(-16.495258, -68.128334)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Demo Directions API"

        initMap()
    }

    private fun initMap() {
        val mapFrag = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFrag.getMapAsync(this)
    }

    override fun onMapReady(gmap: GoogleMap?) {
        if(gmap != null)
            googleMap = gmap

        setUpBounds()
        getDirectionsDetails(inicioLatLng, finLatLng, TravelMode.WALKING)
    }

    private fun setUpBounds() {
        googleMap.setOnMapLoadedCallback {
            runOnUiThread {
                val builder = LatLngBounds.Builder()
                builder.include(inicioLatLng.toAndroidLatLng())
                builder.include(finLatLng.toAndroidLatLng())
                val llBounds = builder.build()
                googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(llBounds, 50))
            }
        }
    }

    private fun LatLng.toAndroidLatLng() : com.google.android.gms.maps.model.LatLng {
        return com.google.android.gms.maps.model.LatLng(this.lat, this.lng)
    }

    private fun getDirectionsDetails(origen: LatLng, destino: LatLng, modo: TravelMode) {
        val hora = DateTime()
        try {
            val req = DirectionsApi.newRequest(geoApiContext())
                    .mode(modo)
                    .origin(origen)
                    .destination(destino)
                    .departureTime(hora)
                    //.waypoints(LatLng(-16.495281, -68.136717))

            req.setCallback(object: PendingResult.Callback<DirectionsResult> {
                override fun onFailure(e: Throwable?) {

                }

                override fun onResult(result: DirectionsResult?) {
                    runOnUiThread {
                        if(result != null)
                            addPolyline(result)
                    }
                }
            })

        } catch (e: ApiException) {
            e.printStackTrace()
        }
    }

    private fun addPolyline(results: DirectionsResult) {
        val route = results.routes[0].legs[0]
        val duration = route.duration
        tvTiempoEstimado.text = duration.humanReadable
        results.routes
                .map {
                    PolyUtil.decode(it.overviewPolyline.encodedPath)
                }.map {
                    googleMap.addPolyline(PolylineOptions().addAll(it))
                }.forEach {
                    it.color = ActivityCompat.getColor(applicationContext, R.color.colorLine)
                }

    }

    private fun geoApiContext(): GeoApiContext{
        val geoApiContext = GeoApiContext()
        return geoApiContext
                .setQueryRateLimit(3)
                .setApiKey(getString(R.string.api_key))
                .setConnectTimeout(1500, TimeUnit.MILLISECONDS)
                .setReadTimeout(1500, TimeUnit.MILLISECONDS)
                .setWriteTimeout(1500, TimeUnit.MILLISECONDS)
    }


}
