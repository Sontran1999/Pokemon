package com.example.pokemon.models.species

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class GrowthRate {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null
}