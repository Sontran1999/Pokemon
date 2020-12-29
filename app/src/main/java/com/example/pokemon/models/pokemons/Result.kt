package com.example.pokemon.models.pokemons

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Result {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

}