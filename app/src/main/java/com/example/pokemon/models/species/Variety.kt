package com.example.pokemon.models.species

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Variety {
    @SerializedName("is_default")
    @Expose
    var isDefault: Boolean? = null

    @SerializedName("pokemon")
    @Expose
    var pokemon: Pokemon? = null
}