package com.example.pokemon.models.detailpokemon

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DetailPokemon (

	@SerializedName("abilities") val abilities : List<Abilities>,
	@SerializedName("base_experience") val base_experience : Int,
	@SerializedName("forms") val forms : List<Forms>,
	@SerializedName("game_indices") val game_indices : List<GameIndices>,
	@SerializedName("height") val height : Int,
	@SerializedName("held_items") val held_items : List<String>,
	@SerializedName("id") val id : Int,
	@SerializedName("is_default") val is_default : Boolean,
	@SerializedName("location_area_encounters") val location_area_encounters : String,
	@SerializedName("moves") val moves : List<Moves>,
	@SerializedName("name") val name : String,
	@SerializedName("order") val order : Int,
	@SerializedName("species") val species : Species,
	@SerializedName("sprites") val sprites : Sprites,
	@SerializedName("stats") val stats : List<Stats>,
	@SerializedName("types") val types : List<Types>,
	@SerializedName("weight") val weight : Int
): Serializable