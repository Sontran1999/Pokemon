package com.example.pokemon.models.species

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class EvolutionChain {
    @SerializedName("url")
    @Expose
    var url: String? = null
}