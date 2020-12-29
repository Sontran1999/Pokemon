package com.example.pokemon.models.detailpokemon

import com.google.gson.annotations.SerializedName

data class GenerationVII (

	@SerializedName("icons") val icons : Icons,
	@SerializedName("ultra-sun-ultra-moon") val ultraSunUltraMoon : UltraSunUltraMoon
)