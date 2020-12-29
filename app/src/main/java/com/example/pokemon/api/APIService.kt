package com.example.pokemon.api
import com.example.pokemon.models.detailpokemon.DetailPokemon
import com.example.pokemon.models.pokemons.Pokemons
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*

interface APIService {
    @GET("pokemon/")
    fun getPokemon(): Call<Pokemons>

    @GET("pokemon/{id}/")
    fun getDetailPokemon(@Path("id") id: String?): Call<DetailPokemon>
}