package com.example.pokemon.models.detailpokemon

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class OfficialArtwork {
	@SerializedName("front_default")
	@Expose
	var frontDefault: String? = null
}