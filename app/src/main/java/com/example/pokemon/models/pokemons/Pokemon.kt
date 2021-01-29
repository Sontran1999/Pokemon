package com.example.pokemon.models.pokemons

import android.os.Parcel
import android.os.Parcelable
import com.example.pokemon.models.detailpokemon.DetailPokemon
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Pokemon constructor( types: String){
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    var id: String? = null

    var type: String? = null

}