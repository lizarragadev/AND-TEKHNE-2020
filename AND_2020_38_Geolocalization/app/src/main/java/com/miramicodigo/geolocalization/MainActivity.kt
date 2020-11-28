package com.miramicodigo.geolocalization

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.provider.Settings
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.util.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private var locationManager: LocationManager? = null

    private var handler: Handler? = null

    private var disponibleGeocoder: Boolean = false
    private var usarFino: Boolean = false
    private var usarAmbos: Boolean = false

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            usarFino = savedInstanceState.getBoolean(CLAVE_FINO)
            usarAmbos = savedInstanceState.getBoolean(CLAVE_AMBOS)
        } else {
            usarFino = false
            usarAmbos = false
        }

        disponibleGeocoder = true

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        handler = @SuppressLint("HandlerLeak")
        object : Handler() {
            override fun handleMessage(msg: Message) {
                when(msg!!.what) {
                    ACTUALIZAR_LATITUD -> textoLatitud.text = msg.obj.toString()
                    ACTUALIZAR_LONGITUD -> textoLongitud.text = msg.obj.toString()
                    ACTUALIZAR_DIRECCION -> textoDireccion.text = msg.obj.toString()
                }
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(CLAVE_FINO, usarFino)
        outState.putBoolean(CLAVE_AMBOS, usarAmbos)
    }

    fun abrirConfiguracionesUbicacionYSeguridad() {
        startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
    }

    override fun onStart() {
        super.onStart()
        val estadoGPS = locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if(!estadoGPS) {
            val dialog = AlertDialog.Builder(this)
            dialog.setMessage("¿Quieres habilitar el GPS?")
            dialog.setCancelable(false)
            dialog.setPositiveButton("SI") { dialogInterface, i ->
                abrirConfiguracionesUbicacionYSeguridad()
            }
            dialog.setNegativeButton("NO") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            val alerta = dialog.create()
            alerta.show()
        }
    }

    override fun onResume() {
        super.onResume()
        configurar()
    }

    override fun onStop() {
        super.onStop()
        locationManager!!.removeUpdates(escuchador)
    }

    private val escuchador = object : LocationListener {

        override fun onLocationChanged(location: Location) {
            actualizarIU(location)
        }

        override fun onProviderDisabled(provider: String) {

        }

        override fun onProviderEnabled(provider: String) {

        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {

        }
    }

    fun configurar() {
        var localizacionGPS: Location? = null
        var localizacionRED: Location? = null
        textoLatitud.text = resources.getString(R.string.desconocido)
        textoLongitud.text = resources.getString(R.string.desconocido)
        textoDireccion.text = resources.getString(R.string.desconocido)
        locationManager!!.removeUpdates(escuchador)

        if (usarFino) {
            botonFino.setBackgroundResource(R.drawable.degradado_naranja)
            botonAmbos.setBackgroundResource(R.drawable.degradado_celeste)
            localizacionGPS = pedirActualizacionesDeProveedor(LocationManager.GPS_PROVIDER, R.string.gps_no_soportado)
            if(localizacionGPS != null)
                actualizarIU(localizacionGPS)

        } else if (usarAmbos) {
            botonFino.setBackgroundResource(R.drawable.degradado_celeste)
            botonAmbos.setBackgroundResource(R.drawable.degradado_naranja)
            localizacionGPS = pedirActualizacionesDeProveedor(LocationManager.GPS_PROVIDER, R.string.gps_no_soportado)
            localizacionRED = pedirActualizacionesDeProveedor(LocationManager.NETWORK_PROVIDER, R.string.gps_no_soportado)
            if(localizacionGPS != null && localizacionRED != null) {
                actualizarIU(getMejorLocalizacion(localizacionGPS, localizacionRED))
            } else {
                if(localizacionGPS != null) {
                    actualizarIU(localizacionGPS)
                } else {
                    if(localizacionRED != null) {
                        actualizarIU(localizacionRED)
                    } else {
                        //xxxxxx
                    }
                }
            }

        }
    }

    private fun pedirActualizacionesDeProveedor(proveedor: String, mensajeError: Int): Location? {
        var localizacion: Location? = null
        if(locationManager!!.isProviderEnabled(proveedor)) {
            val TIEMPO = 10000
            val DISTANCIA = 10
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager!!.requestLocationUpdates(proveedor, TIEMPO.toLong(), DISTANCIA.toFloat(), escuchador)
                localizacion = locationManager!!.getLastKnownLocation(proveedor)
            } else {
                solicitarPermiso()
            }
        }
        return localizacion
    }

    fun solicitarPermiso() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {

        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 1) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                configurar()
            }
        }
    }

    fun usarProveedorFino(view: View) {
        usarFino = true
        usarAmbos = false
        configurar()
    }

    fun usarAmbosProveedores(view: View) {
        usarFino = false
        usarAmbos = true
        configurar()
    }

    private fun hacerGeocodificacionReversa(location: Location) {
        TareaGeocodificacionReversa(this).execute(*arrayOf<Location>(location))
    }

    private fun actualizarIU(localizacion: Location) {
        Message.obtain(handler, ACTUALIZAR_LATITUD, localizacion.latitude.toString()).sendToTarget()
        Message.obtain(handler, ACTUALIZAR_LONGITUD, localizacion.longitude.toString()).sendToTarget()
        if(disponibleGeocoder)
            hacerGeocodificacionReversa(localizacion)
    }

    protected fun getMejorLocalizacion(newLocation: Location,
                                       currentBestLocation: Location?): Location {
        val TWO_MINUTES = 1000 * 60 * 2
        if (currentBestLocation == null) {
            return newLocation
        }

        val timeDelta = newLocation.time - currentBestLocation!!.time
        val isSignificantlyNewer = timeDelta > TWO_MINUTES
        val isSignificantlyOlder = timeDelta < -TWO_MINUTES
        val isNewer = timeDelta > 0

        if (isSignificantlyNewer) {
            return newLocation
        } else if (isSignificantlyOlder) {
            return currentBestLocation
        }

        val accuracyDelta = (newLocation.accuracy - currentBestLocation!!.accuracy) as Int
        val isLessAccurate = accuracyDelta > 0
        val isMoreAccurate = accuracyDelta < 0
        val isSignificantlyLessAccurate = accuracyDelta > 200

        val isFromSameProvider = esMismoProveedor(
                newLocation.provider, currentBestLocation!!.provider)

        if (isMoreAccurate) {
            return newLocation
        } else if (isNewer && !isLessAccurate) {
            return newLocation
        } else if (isNewer && !isSignificantlyLessAccurate
                && isFromSameProvider) {
            return newLocation
        }
        return currentBestLocation
    }

    private fun esMismoProveedor(provider1: String?, provider2: String?): Boolean {
        return if (provider1 == null) {
            provider2 == null
        } else provider1 == provider2
    }

    private inner class TareaGeocodificacionReversa(internal var mContext: Context) : AsyncTask<Location, Void, Void>() {

        override fun doInBackground(vararg params: Location): Void? {
            val geocoder = Geocoder(mContext, Locale.getDefault())
            val loc = params[0]
            var address : List<Address>? = null
            try {
                address = geocoder.getFromLocation(loc.latitude, loc.longitude, 1)
            } catch ( e: IOException) {
                Message.obtain(handler, ACTUALIZAR_DIRECCION, e.message).sendToTarget()
            }

            if(address != null && address.isNotEmpty()) {
                val ad = address[0]
                val format = String.format("%s, %s, %s", ad.getAddressLine(0), ad.locality, ad.countryName)
                Message.obtain(handler, ACTUALIZAR_DIRECCION, format).sendToTarget()
            }
            return null
        }
    }

    companion object {

        private val CLAVE_FINO = "usar_fino"
        private val CLAVE_AMBOS = "usar_ambos"

        private val ACTUALIZAR_DIRECCION = 1
        private val ACTUALIZAR_LATITUD = 2
        private val ACTUALIZAR_LONGITUD = 3
    }
}
