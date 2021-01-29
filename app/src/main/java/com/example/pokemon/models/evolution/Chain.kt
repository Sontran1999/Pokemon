package com.example.pokemon.models.evolution

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Chain {
    @SerializedName("evolution_details")
    @Expose
    var evolutionDetails: List<Any>? = null

    @SerializedName("evolves_to")
    @Expose
    var evolvesTo: List<EvolvesTo>? = null

    @SerializedName("is_baby")
    @Expose
    var isBaby: Boolean? = null

    @SerializedName("species")
    @Expose
    var species: Species? = null
}