package com.miramicodigo.restful_covid.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miramicodigo.restful_covid.R
import com.miramicodigo.restful_covid.model.Country
import kotlinx.android.synthetic.main.activity_country.*
import java.text.SimpleDateFormat
import java.util.*

class CountryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)

        supportActionBar?.hide()

        val country = intent.getSerializableExtra("country") as Country

        tvDetallePais.text = country.country
        val formatDate = SimpleDateFormat("dd/MM/yyyy hh:mm ZZ", Locale.getDefault())
        tvDetalleFechaActualizacion.text = formatDate.format(country.date)

        tvDetalleHoyConfirmados.text = country.newConfirmed.toString()
        tvDetalleHoyRecuperados.text = country.newRecovered.toString()
        tvDetalleHoyDecesos.text = country.newDeaths.toString()
        tvDetalleTotalConfirmados.text = country.totalConfirmed.toString()
        tvDetalleTotalRecuperados.text = country.totalRecovered.toString()
        tvDetalleTotalDecesos.text = country.totalDeaths.toString()

    }
}