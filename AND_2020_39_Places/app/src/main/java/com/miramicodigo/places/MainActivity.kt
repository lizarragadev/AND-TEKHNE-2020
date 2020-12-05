package com.miramicodigo.places

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FetchPlaceResponse
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private var adapter: AutocompleteAdapter? = null
    private var placesClient: PlacesClient? = null
    private lateinit var latLng: LatLng
    private var namePlace = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiKey = getString(R.string.api_key)
        if(apiKey.isEmpty()) {
            panelData.visibility = View.INVISIBLE
            panelTemp.visibility = View.VISIBLE
            tvError.text = getString(R.string.error)
            return
        }

        if(!Places.isInitialized()) {
            Places.initialize(this, apiKey)
        }

        placesClient = Places.createClient(this)
        initAutoCompleteTextView()
    }

    private fun initAutoCompleteTextView() {
        auto.threshold = 1
        auto.onItemClickListener = autocompleteClickListener
        adapter = placesClient?.let { AutocompleteAdapter(this, it) }
        auto.setAdapter(adapter)
    }

    @SuppressLint("SetTextI18n")
    private val autocompleteClickListener = OnItemClickListener { adapterView, view, i, l ->
        try {
            hideSoftKeyboard()
            val item: AutocompletePrediction = adapter?.getItem(i) ?: null!!
            var placeID: String? = null
            placeID = item.placeId

            val placeFields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG,
                    Place.Field.PHONE_NUMBER, Place.Field.WEBSITE_URI, Place.Field.RATING)
            val request: FetchPlaceRequest
            request = FetchPlaceRequest.builder(placeID, placeFields).build()
            placesClient?.fetchPlace(request)?.addOnSuccessListener { task ->
                showDataFromPlaces(task)
            }?.addOnFailureListener { e ->
                panelTemp.visibility = View.VISIBLE
                panelData.visibility = View.INVISIBLE
                tvError.text = e.message
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun showDataFromPlaces(task: FetchPlaceResponse) {
        panelData.visibility = View.VISIBLE
        panelTemp.visibility = View.INVISIBLE
        tvNombre.text = if(task.place.name != null) task.place.name else ""
        tvDireccion.text = if(task.place.address != null) task.place.address else ""
        tvTelefono.text = if(task.place.phoneNumber != null) task.place.phoneNumber else ""
        tvPaginaWeb.text = if(task.place.websiteUri != null) task.place.websiteUri.toString() else ""
        tvCoordenadas.text = if(task.place.latLng != null) task.place.latLng.toString() else ""

        rbValoracion.isEnabled = false
        rbValoracion.max = 5
        rbValoracion.stepSize = 0.01f
        rbValoracion.invalidate()
        if(task.place.rating != null)
            rbValoracion.rating = task.place.rating?.toFloat() ?: 0f
        else
            rbValoracion.rating = 0f

        val mapFrag = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFrag.getMapAsync(this)

        latLng = task.place.latLng ?: null!!
        namePlace = task.place.name.toString()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val cenZoom = CameraUpdateFactory.newLatLngZoom(latLng, 17f)
        googleMap.moveCamera(cenZoom)

        val mark = MarkerOptions()
                .position(latLng)
                .title(namePlace)

        googleMap.addMarker(mark)

        googleMap.uiSettings.isZoomGesturesEnabled = false
        googleMap.uiSettings.isTiltGesturesEnabled = false
        googleMap.uiSettings.isScrollGesturesEnabled = false
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.uiSettings.isRotateGesturesEnabled = false
    }

    fun hideSoftKeyboard() {
        if(currentFocus != null) {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }
}