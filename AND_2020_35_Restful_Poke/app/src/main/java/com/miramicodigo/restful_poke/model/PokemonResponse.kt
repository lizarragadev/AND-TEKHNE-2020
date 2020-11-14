package com.miramicodigo.restful_poke.model

import com.google.gson.annotations.SerializedName

class PokemonResponse {
    @SerializedName("results")
    var results: ArrayList<Pokemon>? = null
}