package com.example.pokemon.models.species

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class PokedexNumber {
    @SerializedName("entry_number")
    @Expose
    var entryNumber: Int? = null

    @SerializedName("pokedex")
    @Expose
    var pokedex: Pokedex? = null
}