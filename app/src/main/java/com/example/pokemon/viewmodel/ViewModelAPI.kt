package com.example.pokemon.viewmodel

import android.util.Log
import android.widget.Adapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemon.adapter.PokemonAdapter
import com.example.pokemon.models.pokemons.PokemonResponse
import com.example.pokemon.api.APIService
import com.example.pokemon.api.ApiUtils
import com.example.pokemon.models.detailpokemon.DetailPokemon
import com.example.pokemon.models.detailpokemon.Move
import com.example.pokemon.models.evolution.Evolution
import com.example.pokemon.models.pokemons.Pokemon
import com.example.pokemon.models.species.Species
import retrofit2.Call
import retrofit2.Response

class ViewModelAPI : ViewModel() {
    private val apiService: APIService = ApiUtils().getAPIService()
    var pokemons: MutableLiveData<List<Pokemon>?> = MutableLiveData()
    var detailPokemon: MutableLiveData<DetailPokemon?> = MutableLiveData()
    var species: MutableLiveData<Species> = MutableLiveData()
    var evoultion: MutableLiveData<Evolution> = MutableLiveData()
    var versionPokemon: MutableLiveData<DetailPokemon> = MutableLiveData()

    fun getAllPokemon(listSize: Int) {
        apiService.getPokemon(listSize, 40)
            .enqueue(object : retrofit2.Callback<PokemonResponse> {
                override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                    Log.d("pokemon", "error loading from API getAll")
                    pokemons.postValue(null)
                }

                override fun onResponse(
                    call: Call<PokemonResponse>,
                    response: Response<PokemonResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.d("pokemon", "pokemon loaded from API")
                        response.body()?.results?.let { _result ->

                            _result.map { pokemon ->
                                val pokemonId = "/\\d+/$".toRegex().find(pokemon.url ?: "")?.value

                                pokemonId?.apply {
                                    pokemon.id = substring(1, pokemonId.length - 1)
                                    getDetailPokemon(pokemon.id.toString())
                                }

                                return@map pokemon
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

    fun getDetailPokemon(pokemonId: String) {
        apiService.getDetailPokemon(pokemonId)
            .enqueue(object : retrofit2.Callback<DetailPokemon> {
                override fun onFailure(call: Call<DetailPokemon>, t: Throwable) {
                    Log.d("", "error loading from API getDetail")
                }

                override fun onResponse(
                    call: Call<DetailPokemon>,
                    response: Response<DetailPokemon>
                ) {
                    if (response.isSuccessful) {
                        Log.d("aaa", "pokemon loaded from API $pokemonId")
                        response.body()?.let { detail ->
                            pokemons.value?.let { pokemonList ->
                                pokemons.postValue(pokemonList.map { pokemon ->
                                    if (pokemon.id.equals(pokemonId)) {
                                        pokemon.detailPokemon = detail
                                    }

                                    return@map pokemon
                                })
                            }
                        }
                        detailPokemon.postValue(response.body())
                    } else {
                        Log.d("aaa", response.message() + response.code())
                    }
                }

            })
    }

    fun getVersionPokemon(pokemonId: String) {
        apiService.getDetailPokemon(pokemonId)
            .enqueue(object : retrofit2.Callback<DetailPokemon> {
                override fun onFailure(call: Call<DetailPokemon>, t: Throwable) {
                    Log.d("", "error loading from API getDetail")
                }

                override fun onResponse(
                    call: Call<DetailPokemon>,
                    response: Response<DetailPokemon>
                ) {
                    if (response.isSuccessful) {
                        versionPokemon.postValue(response.body())
                    } else {
                        Log.d("aaa", response.message() + response.code())
                    }
                }
            })
    }

    fun searchPokemon(query: String, list: MutableList<Pokemon>) {
        var listSearch: MutableList<Pokemon> = mutableListOf()
        list?.forEachIndexed { index, pokemon ->
            var name = pokemon.name.toString()
            if (query.toUpperCase().equals(name.toUpperCase())) {
                listSearch.add(pokemon)
            } else if (query.toLowerCase().equals(name.toLowerCase())) {
                listSearch.add(pokemon)
            }
        }
        pokemons.postValue(listSearch)
    }

    fun getSpecies(id: String) {
        apiService.getSpecies(id)
            .enqueue(object : retrofit2.Callback<Species> {
                override fun onFailure(call: Call<Species>, t: Throwable) {
                    Log.d("", "error loading from API getSpecies")
                }

                override fun onResponse(
                    call: Call<Species>,
                    response: Response<Species>
                ) {
                    if (response.isSuccessful) {
                        species.postValue(response.body())
                    } else {
                        Log.d("aaa", response.message() + response.code())
                    }
                }

            })
    }

    fun getEvolution(id: String){
        apiService.getEvolution(id)
            .enqueue(object : retrofit2.Callback<Evolution> {
                override fun onFailure(call: Call<Evolution>, t: Throwable) {
                    Log.d("", "error loading from API getSpecies")
                }

                override fun onResponse(
                    call: Call<Evolution>,
                    response: Response<Evolution>
                ) {
                    if (response.isSuccessful) {
                        evoultion.postValue(response.body())
                    } else {
                        Log.d("aaa", response.message() + response.code())
                    }
                }

            })
    }
}