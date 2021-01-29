package com.example.pokemon.models.detailpokemon

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class GenerationVII {
	@SerializedName("icons")
	@Expose
	var icons: Icons? = null

	@SerializedName("ultra-sun-ultra-moon")
	@Expose
	var ultraSunUltraMoon: UltraSunUltraMoon? = null
}