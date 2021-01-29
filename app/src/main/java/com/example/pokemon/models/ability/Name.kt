package com.example.pokemon.models.ability

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Name {
    @SerializedName("language")
    @Expose
    var language: Language? = null

    @SerializedName("name")
    @Expose
    var name: String? = null
}