package com.example.pokemon.models.detailpokemon

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class GenerationV {
	@SerializedName("black-white")
	@Expose
	var blackWhite: BlackWhite? = null
}