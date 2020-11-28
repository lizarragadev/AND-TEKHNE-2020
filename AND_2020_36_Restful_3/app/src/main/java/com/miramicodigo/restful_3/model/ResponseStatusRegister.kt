package com.miramicodigo.restful_3.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ResponseStatusRegister(
        @SerializedName("status")
        var status: Int?,
        @SerializedName("message")
        var message: String?
) : Serializable