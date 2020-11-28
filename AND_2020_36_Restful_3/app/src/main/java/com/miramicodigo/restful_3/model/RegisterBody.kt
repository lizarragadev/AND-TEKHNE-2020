package com.miramicodigo.restful_3.model

import com.google.gson.annotations.SerializedName

class RegisterBody(
    @SerializedName("id")
    var userId: String?,
    @SerializedName("password")
    var password: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("address")
    var address: String?,
    @SerializedName("gender")
    var gender: String?
)