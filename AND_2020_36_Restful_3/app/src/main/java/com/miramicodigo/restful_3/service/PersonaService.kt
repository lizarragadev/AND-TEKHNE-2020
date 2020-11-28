package com.miramicodigo.restful_3.service

import com.miramicodigo.restful_3.model.LoginBody
import com.miramicodigo.restful_3.model.RegisterBody
import com.miramicodigo.restful_3.model.ResponsePersonaLogin
import com.miramicodigo.restful_3.model.ResponseStatusRegister
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PersonaService {

    @POST("usuarios/register")
    fun register(@Body registerBody: RegisterBody): Call<ResponseStatusRegister>

    @POST("usuarios/login")
    fun login(@Body loginBody: LoginBody): Call<ResponsePersonaLogin>

    companion object {
        val BASE_URL = "https://serene-sea-64491.herokuapp.com/"
    }
}