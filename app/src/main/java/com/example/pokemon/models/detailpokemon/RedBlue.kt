package com.example.pokemon.models.detailpokemon

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class RedBlue {
	@SerializedName("back_default")
	@Expose
	var backDefault: String? = null

	@SerializedName("back_gray")
	@Expose
	var backGray: String? = null

	@SerializedName("front_default")
	@Expose
	var frontDefault: String? = null

	@SerializedName("front_gray")
	@Expose
	var frontGray: String? = null
}