package com.miramicodigo.restful_covid.model

import com.google.gson.annotations.SerializedName

class Global {
    @SerializedName("NewConfirmed")
    var newConfirmed : Long = 0
    @SerializedName("TotalConfirmed")
    var totalConfirmed : Long = 0
    @SerializedName("NewDeaths")
    var newDeaths : Long = 0
    @SerializedName("TotalDeaths")
    var totalDeaths : Long = 0
    @SerializedName("NewRecovered")
    var newRecovered : Long = 0
    @SerializedName("TotalRecovered")
    var totalRecovered : Long = 0
}