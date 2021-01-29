package com.example.pokemon.models.species

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class PalParkEncounter {
    @SerializedName("area")
    @Expose
    var area: Area? = null

    @SerializedName("base_score")
    @Expose
    var baseScore: Int? = null

    @SerializedName("rate")
    @Expose
    var rate: Int? = null
}