package com.example.pokemon.models.detailpokemon

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class GenerationII {
	@SerializedName("crystal")
	@Expose
	var crystal: Crystal? = null

	@SerializedName("gold")
	@Expose
	var gold: Gold? = null

	@SerializedName("silver")
	@Expose
	var silver: Silver? = null
}