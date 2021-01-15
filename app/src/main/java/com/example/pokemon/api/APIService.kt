package com.example.pokemon.api

import com.example.pokemon.models.detailpokemon.Abilities
import com.example.pokemon.models.detailpokemon.DetailPokemon
import com.example.pokemon.models.detailpokemon.Species
import com.example.pokemon.models.pokemons.PokemonResponse
import retrofit2.Call
import retrofit2.http.*

interface APIService {
    @GET("pokemon/")
    fun getPokemon(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
        ): Call<PokemonResponse>

    @GET("pokemon/{id}/")
    fun getDetailPokemon(@Path("id") id: String): Call<DetailPokemon>

    @GET("ability/{id}/")
    fun getTorrent(@Path("id") id: String): Call<Abilities>

    @GET("pokemon-species/{id}/")
    fun getSpecies(@Path("id") id:String): Call<Species>
}