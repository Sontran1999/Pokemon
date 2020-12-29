package com.example.pokemon.models.detailpokemon

import com.google.gson.annotations.SerializedName


data class GenerationIII (

	@SerializedName("emerald") val emerald : Emerald,
	@SerializedName("firered-leafgreen") val fireRedLeafGreen : FireredLeafgreen,
	@SerializedName("ruby-sapphire") val rubySapphire : RubySapphire
)