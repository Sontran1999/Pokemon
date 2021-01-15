package com.example.pokemon.models.Ability

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Pokemon_ {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null
}