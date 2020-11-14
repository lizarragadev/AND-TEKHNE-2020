package com.miramicodigo.restful_covid.service

import com.miramicodigo.restful_covid.model.GlobalResults
import retrofit2.Call
import retrofit2.http.GET

interface CovidInterface {
    @GET("summary")
    fun obtenerGlobalResults(): Call<GlobalResults>
}