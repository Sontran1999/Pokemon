package com.example.pokemon.models.detailpokemon

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import com.example.pokemon.adapter.PokemonAdapter.Companion.VIEW_TYPE_ITEM

class DetailPokemon() : Serializable, Parcelable {
	@SerializedName("abilities")
	@Expose
	var abilities: List<Ability>? = null

	@SerializedName("base_experience")
	@Expose
	var baseExperience: Int? = null

	@SerializedName("forms")
	@Expose
	var forms: List<Forms>? = null

	@SerializedName("game_indices")
	@Expose
	var gameIndices: List<GameIndices>? = null

	@SerializedName("height")
	@Expose
	var height: Int? = null

	@SerializedName("held_items")
	@Expose
	var heldItems: List<HeldItem>? = null

	@SerializedName("id")
	@Expose
	var id: Int? = null

	@SerializedName("is_default")
	@Expose
	var isDefault: Boolean? = null

	@SerializedName("location_area_encounters")
	@Expose
	var locationAreaEncounters: String? = null

	@SerializedName("moves")
	@Expose
	var moves: List<Move>? = null

	@SerializedName("name")
	@Expose
	var name: String? = null

	@SerializedName("order")
	@Expose
	var order: Int? = null

	@SerializedName("species")
	@Expose
	var species: Species? = null

	@SerializedName("sprites")
	@Expose
	var sprites: Sprites? = null

	@SerializedName("stats")
	@Expose
	var stats: List<Stat>? = null

	@SerializedName("types")
	@Expose
	var types: List<Type>? = null

	@SerializedName("weight")
	@Expose
	var weight: Int? = null

	var itemType: Int = VIEW_TYPE_ITEM

	constructor(parcel: Parcel) : this() {
		baseExperience = parcel.readValue(Int::class.java.classLoader) as? Int
		height = parcel.readValue(Int::class.java.classLoader) as? Int
		id = parcel.readValue(Int::class.java.classLoader) as? Int
		isDefault = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
		locationAreaEncounters = parcel.readString()
		name = parcel.readString()
		order = parcel.readValue(Int::class.java.classLoader) as? Int
		weight = parcel.readValue(Int::class.java.classLoader) as? Int
	}

	constructor(itemType: Int) : this() {
		this.itemType = itemType
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeValue(baseExperience)
		parcel.writeValue(height)
		parcel.writeValue(id)
		parcel.writeValue(isDefault)
		parcel.writeString(locationAreaEncounters)
		parcel.writeString(name)
		parcel.writeValue(order)
		parcel.writeValue(weight)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<DetailPokemon> {
		override fun createFromParcel(parcel: Parcel): DetailPokemon {
			return DetailPokemon(parcel)
		}

		override fun newArray(size: Int): Array<DetailPokemon?> {
			return arrayOfNulls(size)
		}
	}
}