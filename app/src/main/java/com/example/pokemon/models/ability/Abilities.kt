package com.example.pokemon.models.ability

import com.example.pokemon.models.pokemons.Pokemon
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Abilities {
    @SerializedName("effect_changes")
    @Expose
    var effectChanges: List<Any>? = null

    @SerializedName("effect_entries")
    @Expose
    var effectEntries: List<EffectEntry>? = null

    @SerializedName("flavor_text_entries")
    @Expose
    var flavorTextEntries: List<FlavorTextEntry>? = null

    @SerializedName("generation")
    @Expose
    var generation: Generation? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("is_main_series")
    @Expose
    var isMainSeries: Boolean? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("names")
    @Expose
    var names: List<Name>? = null

    @SerializedName("pokemon")
    @Expose
    var pokemon: List<Pokemon>? = null
}