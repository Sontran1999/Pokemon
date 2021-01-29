package com.example.pokemon.models.detailpokemon

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MoveLearnMethod (

	@SerializedName("name") val name : String,
	@SerializedName("url") val url : String
): Serializable