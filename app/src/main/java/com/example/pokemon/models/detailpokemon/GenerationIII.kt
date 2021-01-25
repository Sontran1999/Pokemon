package com.example.pokemon.models.detailpokemon

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class GenerationIII {
	@SerializedName("emerald")
	@Expose
	var emerald: Emerald? = null

	@SerializedName("firered-leafgreen")
	@Expose
	var fireredLeafgreen: FireredLeafgreen? = null

	@SerializedName("ruby-sapphire")
	@Expose
	var rubySapphire: RubySapphire? = null
}