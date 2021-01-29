package com.example.pokemon.models.detailpokemon

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Move {
	@SerializedName("move")
	@Expose
	var move: Moves? = null

	@SerializedName("version_group_details")
	@Expose
	var versionGroupDetails: List<VersionGroupDetails>? = null
}