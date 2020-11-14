package com.miramicodigo.restful_covid.model

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

class GlobalResults {
    @SerializedName("Global")
    lateinit var global: Global
    @SerializedName("Countries")
    lateinit var countries: ArrayList<Country>
    @SerializedName("Date")
    lateinit var date: Date
}