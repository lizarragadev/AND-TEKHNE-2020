package com.miramicodigo.restful_3.model

import com.google.gson.annotations.SerializedName

class LoginBody(
        @SerializedName("id")
        var userId: String?,
        @SerializedName("password")
        var password: String?
)