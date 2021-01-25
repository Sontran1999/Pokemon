package com.example.pokemon.models.detailpokemon

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class GenerationIV {
	@SerializedName("diamond-pearl")
	@Expose
	var diamondPearl: DiamondPearl? = null

	@SerializedName("heartgold-soulsilver")
	@Expose
	var heartgoldSoulsilver: HeartGoldSoulSilver? = null

	@SerializedName("platinum")
	@Expose
	var platinum: Platinum? = null
}