package com.example.pokemon.models.detailpokemon

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class GenerationVI {
	@SerializedName("omegaruby-alphasapphire")
	@Expose
	var omegarubyAlphasapphire: OmegaRubyAlphaSapphire? = null

	@SerializedName("x-y")
	@Expose
	var xY: XY? = null
}