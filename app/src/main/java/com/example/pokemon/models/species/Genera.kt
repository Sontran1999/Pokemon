package com.example.pokemon.models.species

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Genera {
    @SerializedName("genus")
    @Expose
    var genus: String? = null

    @SerializedName("language")
    @Expose
    var language: Language? = null
}