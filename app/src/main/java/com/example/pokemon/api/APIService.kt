package com.example.pokemon.api
import com.example.pokemon.models.detailpokemon.DetailPokemon
import com.example.pokemon.models.pokemons.PokemonResponse
import retrofit2.Call
import retrofit2.http.*

interface APIService {
    @GET("pokemon/")
    fun getPokemon(): Call<PokemonResponse>

    @GET("pokemon/{id}/")
    fun getDetailPokemon(@Path("id") id: Int): Call<DetailPokemon>
}