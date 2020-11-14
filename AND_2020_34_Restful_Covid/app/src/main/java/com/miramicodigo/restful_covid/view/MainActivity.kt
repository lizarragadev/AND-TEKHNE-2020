package com.miramicodigo.restful_covid.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miramicodigo.restful_covid.R
import com.miramicodigo.restful_covid.model.GlobalResults
import com.miramicodigo.restful_covid.service.CovidInterface
import com.miramicodigo.restful_covid.view.adapter.RVAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.content_progress.*
import kotlinx.android.synthetic.main.content_scrolling.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var retrofit: Retrofit
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var adaptador: RecyclerView.Adapter<*>
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        collapsingTL.title = "COVID APP"
        context = this

        retrofit = Retrofit.Builder()
                .baseUrl("https://api.covid19api.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvDatos.layoutManager = layoutManager

        obtenerDatos()
    }

    private fun obtenerDatos() {
        val service = retrofit.create(CovidInterface::class.java)
        val covidResponseCall = service.obtenerGlobalResults()

        rlProgress.visibility = View.VISIBLE

        covidResponseCall.enqueue(object: Callback<GlobalResults> {
            override fun onResponse(call: Call<GlobalResults>, response: Response<GlobalResults>) {
                rlProgress.visibility = View.INVISIBLE
                if(response.isSuccessful) {
                    val dataResponse = response.body() as GlobalResults
                    showResults(dataResponse)
                }
            }

            override fun onFailure(call: Call<GlobalResults>, t: Throwable) {
                rlProgress.visibility = View.INVISIBLE
            }

        })
    }

    private fun showResults(globalRes: GlobalResults) {
        adaptador = RVAdapter(context, globalRes.countries)
        rvDatos.adapter = adaptador

        val global = globalRes.global
        tvHoyConfirmados.text = global.newConfirmed.toString()
        tvHoyRecuperados.text = global.newRecovered.toString()
        tvHoyDecesos.text = global.newDeaths.toString()

        tvTotalConfirmados.text = global.totalConfirmed.toString()
        tvTotalRecuperados.text = global.totalRecovered.toString()
        tvTotalDecesos.text = global.totalDeaths.toString()

        val formatDate = SimpleDateFormat("dd/MM/yyyy hh:mm ZZ", Locale.getDefault())

        tvFechaActualizacion.text = formatDate.format(globalRes.date)
    }

}