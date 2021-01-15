package com.example.pokemon.models.Ability

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class EffectEntry {
    @SerializedName("effect")
    @Expose
    var effect: String? = null

    @SerializedName("language")
    @Expose
    var language: Language? = null

    @SerializedName("short_effect")
    @Expose
    var shortEffect: String? = null
}