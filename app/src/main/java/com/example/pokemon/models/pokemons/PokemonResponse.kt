package com.example.pokemon.models.pokemons

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class PokemonResponse() {
    constructor(
       pokemonResponse: PokemonResponse
    ) : this() {
        this.count = pokemonResponse.count
        this.next = pokemonResponse.next
        this.previous = pokemonResponse.previous
        this.results = pokemonResponse.results
    }
    @SerializedName("count")
    @Expose
    var count: Int? = null

    @SerializedName("next")
    @Expose
    var next: String? = null

    @SerializedName("previous")
    @Expose
    var previous: Any? = null

    @SerializedName("results")
    @Expose
    var results: List<Pokemon>? = null

}