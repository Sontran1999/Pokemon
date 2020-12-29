package com.example.pokemon.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemon.models.pokemons.PokemonResponse
import com.example.pokemon.api.APIService
import com.example.pokemon.api.ApiUtils
import com.example.pokemon.models.detailpokemon.DetailPokemon
import com.example.pokemon.models.pokemons.Pokemon
import retrofit2.Call
import retrofit2.Response

class ViewModelAPI : ViewModel() {
    private val apiService: APIService = ApiUtils().getAPIService()
    var pokemons: MutableLiveData<List<Pokemon>?> = MutableLiveData()

    fun getAllPokemon() {
        apiService.getPokemon()
            .enqueue(object : retrofit2.Callback<PokemonResponse> {
                override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                    Log.d("pokemon", "error loading from ApPI")
                    pokemons.postValue(null)
                }

                override fun onResponse(
                    call: Call<PokemonResponse>,
                    response: Response<PokemonResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.d("pokemon", "pokemon loaded from ApPI")
                        response.body()?.results?.let { _result ->

                            _result.map { _pokemon ->
                                val _pokemonId = "/\\d+/$".toRegex().find(_pokemon.url ?: "")?.value

                                _pokemonId?.apply {
                                    _pokemon.id = substring(1, _pokemonId.length - 1).toInt()
                                    getDetailPokemon(_pokemon.id)
                                }

                                return@map _pokemon
                            }

                            pokemons.postValue(_result)
                        }
                    } else {
                        Log.d("pokemon", response.message() + response.code())
                        pokemons.postValue(null)
                    }
                }

            })
    }

    fun getDetailPokemon(pokemonId: Int) {
        Log.d("Binh", "Pokemon: $pokemonId")

        apiService.getDetailPokemon(pokemonId)
            .enqueue(object : retrofit2.Callback<DetailPokemon> {
                override fun onFailure(call: Call<DetailPokemon>, t: Throwable) {
                    Log.d("", "error loading from ApPI")
                }

                override fun onResponse(
                    call: Call<DetailPokemon>,
                    response: Response<DetailPokemon>
                ) {
                    if (response.isSuccessful) {
                        Log.d("aaa", "pokemon loaded from ApPI $pokemonId")
                        response.body()?.let { _detail ->
                            pokemons.value?.let { _pokemonList ->
                                pokemons.postValue(_pokemonList.map { _pokemon ->
                                    if (_pokemon.id == pokemonId) {
                                        _pokemon.detailPokemon = _detail
                                    }

                                    return@map _pokemon
                                })
                            }
                        }
                    } else {
                        Log.d("aaa", response.message() + response.code())
                    }
                }

            })

    }
}