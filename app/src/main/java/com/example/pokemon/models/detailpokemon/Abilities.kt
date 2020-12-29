package com.example.pokemon.models.detailpokemon

import com.google.gson.annotations.SerializedName


data class Abilities (

	@SerializedName("ability") val ability : Ability,
	@SerializedName("is_hidden") val is_hidden : Boolean,
	@SerializedName("slot") val slot : Int
)