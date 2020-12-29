package com.example.pokemon.models.detailpokemon

import com.google.gson.annotations.SerializedName

data class RedBlue (

	@SerializedName("back_default") val back_default : String,
	@SerializedName("back_gray") val back_gray : String,
	@SerializedName("front_default") val front_default : String,
	@SerializedName("front_gray") val front_gray : String
)