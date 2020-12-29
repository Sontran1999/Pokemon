package com.example.pokemon.models.detailpokemon

import com.google.gson.annotations.SerializedName

data class Stats (

	@SerializedName("base_stat") val base_stat : Int,
	@SerializedName("effort") val effort : Int,
	@SerializedName("stat") val stat : Stat
)