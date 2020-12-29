package com.example.pokemon.models.detailpokemon

import com.google.gson.annotations.SerializedName

data class GenerationIV (

	@SerializedName("diamond-pearl") val diamondPearl : DiamondPearl,
	@SerializedName("heartgold-soulsilver") val heartGoldSoulSilver : HeartGoldSoulSilver,
	@SerializedName("platinum") val platinum : Platinum
)