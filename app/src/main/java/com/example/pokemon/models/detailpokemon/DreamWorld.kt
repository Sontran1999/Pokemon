package com.example.pokemon.models.detailpokemon

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class DreamWorld {
	@SerializedName("front_default")
	@Expose
	var frontDefault: String? = null

	@SerializedName("front_female")
	@Expose
	var frontFemale: Any? = null
}