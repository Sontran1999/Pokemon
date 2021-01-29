package com.example.pokemon.models.detailpokemon

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class GameIndices {
	@SerializedName("game_index")
	@Expose
	var gameIndex: Int? = null

	@SerializedName("version")
	@Expose
	var version: Version? = null
}