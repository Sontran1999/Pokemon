package com.example.pokemon.models.detailpokemon

import com.google.gson.annotations.SerializedName

data class Emerald (

	@SerializedName("front_default") val front_default : String,
	@SerializedName("front_shiny") val front_shiny : String
)