package com.example.pokemon.models.evolution

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Evolution {
    @SerializedName("baby_trigger_item")
    @Expose
    var babyTriggerItem: Any? = null

    @SerializedName("chain")
    @Expose
    var chain: Chain? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null
}