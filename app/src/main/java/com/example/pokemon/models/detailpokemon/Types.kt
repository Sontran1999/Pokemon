package com.example.pokemon.models.detailpokemon

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Types {
	@SerializedName("name")
	@Expose
	var name: String? = null

	@SerializedName("url")
	@Expose
	var url: String? = null
}