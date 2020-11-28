package com.miramicodigo.restful_3.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ResponsePersonaLogin(
        @SerializedName("id")
        var id: String?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("address")
        var address: String?,
        @SerializedName("gender")
        var gender: String?,
        @SerializedName("token")
        var token: String?,
) : Serializable