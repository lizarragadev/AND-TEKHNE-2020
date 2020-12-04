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




        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun showDataFromPlaces(task: FetchPlaceResponse) {

    }

    override fun onMapReady(googleMap: GoogleMap) {
        val cenZoom = CameraUpdateFactory.newLatLngZoom(latLng, 17f)
        googleMap.moveCamera(cenZoom)


    }

    fun hideSoftKeyboard() {
        if(currentFocus != null) {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }
}