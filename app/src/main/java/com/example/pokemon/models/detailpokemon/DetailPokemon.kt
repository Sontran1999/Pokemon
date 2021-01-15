package com.example.pokemon.models.detailpokemon

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DetailPokemon() : Serializable, Parcelable{
	@SerializedName("abilities") val abilities : List<Abilities>? = null
	@SerializedName("base_experience") val base_experience : Int? = null
	@SerializedName("forms") val forms : List<Forms>? = null
	@SerializedName("game_indices") val game_indices : List<GameIndices>? = null
	@SerializedName("height") val height : Int? = null
	@SerializedName("held_items") val held_items : List<String>? = null
	@SerializedName("id") val id : Int? = null
	@SerializedName("is_default") val is_default : Boolean? = null
	@SerializedName("location_area_encounters") val location_area_encounters : String? = null
	@SerializedName("moves") val moves : List<Moves>? = null
	@SerializedName("name") val name : String? = null
	@SerializedName("order") val order : Int? = null
	@SerializedName("species") val species : Species? = null
	@SerializedName("sprites") val sprites : Sprites? = null
	@SerializedName("stats") val stats : List<Stats>? = null
	@SerializedName("types") val types : List<Types>? = null
	@SerializedName("weight") val weight : Int? = null

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

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