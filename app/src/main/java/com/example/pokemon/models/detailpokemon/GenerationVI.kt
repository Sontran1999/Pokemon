package com.example.pokemon.models.detailpokemon

import com.google.gson.annotations.SerializedName

data class GenerationVI (

	@SerializedName("omegaruby-alphasapphire") val omegaRubyAlphaSapphire : OmegaRubyAlphaSapphire,
	@SerializedName("x-y") val xY : XY
)