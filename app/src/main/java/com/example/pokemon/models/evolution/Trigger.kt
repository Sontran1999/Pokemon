package com.example.pokemon.models.evolution

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Trigger {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null
}