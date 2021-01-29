package com.example.pokemon.models.ability

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Pokemon {
    @SerializedName("is_hidden")
    @Expose
    var isHidden: Boolean? = null

    @SerializedName("pokemon")
    @Expose
    var pokemon: Pokemon_? = null

    @SerializedName("slot")
    @Expose
    var slot: Int? = null
}