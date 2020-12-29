package com.example.pokemon.models.detailpokemon

import com.google.gson.annotations.SerializedName

data class GameIndices (

	@SerializedName("game_index") val game_index : Int,
	@SerializedName("version") val version : Version
)