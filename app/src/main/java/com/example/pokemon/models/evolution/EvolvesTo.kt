package com.example.pokemon.models.evolution

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class EvolvesTo {
    @SerializedName("evolution_details")
    @Expose
    var evolutionDetails: List<EvolutionDetail>? = null

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