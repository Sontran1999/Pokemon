package com.example.pokemon.models.pokemons

import com.example.pokemon.models.detailpokemon.DetailPokemon
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Pokemon {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    var id: Int = -1

    var detailPokemon: DetailPokemon? = null

}