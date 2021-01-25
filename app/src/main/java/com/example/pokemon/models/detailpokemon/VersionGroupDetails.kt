package com.example.pokemon.models.detailpokemon

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class VersionGroupDetails {
	@SerializedName("level_learned_at")
	@Expose
	var levelLearnedAt: Int? = null

	@SerializedName("move_learn_method")
	@Expose
	var moveLearnMethod: MoveLearnMethod? = null

	@SerializedName("version_group")
	@Expose
	var versionGroup: VersionGroup? = null
}