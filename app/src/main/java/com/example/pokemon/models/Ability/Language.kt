package com.example.pokemon.models.Ability

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Language {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null
}