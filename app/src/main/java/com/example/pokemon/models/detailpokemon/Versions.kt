package com.example.pokemon.models.detailpokemon

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Versions {
	@SerializedName("generation-i")
	@Expose
	var generationI: GenerationI? = null

	@SerializedName("generation-ii")
	@Expose
	var generationIi: GenerationII? = null

	@SerializedName("generation-iii")
	@Expose
	var generationIii: GenerationIII? = null

	@SerializedName("generation-iv")
	@Expose
	var generationIv: GenerationIV? = null

	@SerializedName("generation-v")
	@Expose
	var generationV: GenerationV? = null

	@SerializedName("generation-vi")
	@Expose
	var generationVi: GenerationVI? = null

	@SerializedName("generation-vii")
	@Expose
	var generationVii: GenerationVII? = null

	@SerializedName("generation-viii")
	@Expose
	var generationViii: GenerationVIII? = null
}