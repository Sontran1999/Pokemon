package com.example.pokemon.models.ability

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Generation {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null
}