package com.example.pokemon.models.pokemons

import android.os.Parcel
import android.os.Parcelable
import com.example.pokemon.models.detailpokemon.DetailPokemon
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Pokemon constructor( types: String): Serializable,Parcelable {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    var id: String? = null

    var detailPokemon: DetailPokemon? = null

    var type: String? = null

    constructor(parcel: Parcel) : this(String()) {
        name = parcel.readString()
        url = parcel.readString()
        id = parcel.readString()
        detailPokemon = parcel.readParcelable(DetailPokemon::class.java.classLoader)
        type = parcel.readString()
    }

    init {
        this.type = types
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(url)
        parcel.writeString(id)
        parcel.writeParcelable(detailPokemon, flags)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pokemon> {
        override fun createFromParcel(parcel: Parcel): Pokemon {
            return Pokemon(parcel)
        }

        override fun newArray(size: Int): Array<Pokemon?> {
            return arrayOfNulls(size)
        }
    }


}