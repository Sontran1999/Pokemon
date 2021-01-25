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
    var pokemons: MutableLiveData<PokemonResponse> = MutableLiveData()
    var detailPokemon: MutableLiveData<DetailPokemon> = MutableLiveData()
    var species: MutableLiveData<Species> = MutableLiveData()
    var evoultion: MutableLiveData<Evolution> = MutableLiveData()
    var idPokemon: MutableLiveData<String> = MutableLiveData()
    var listIdPokemon: MutableLiveData<MutableList<String>> = MutableLiveData()
    var searchPokemon: MutableLiveData<MutableList<DetailPokemon>> = MutableLiveData()

    fun getAllPokemon(listSize: Int) {
        apiService.getPokemon(listSize, 20)
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
                        pokemons.postValue(response.body())
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
                    Log.d("aaa", "error loading from API getDetail")
                    detailPokemon.postValue(null)
                }

                override fun onResponse(
                    call: Call<DetailPokemon>,
                    response: Response<DetailPokemon>
                ) {
                    if (response.isSuccessful) {
                        detailPokemon.postValue(response.body())
                    } else {
                        Log.d("aaa", response.message() + response.code())
                        detailPokemon.postValue(null)
                    }
                }

            })
    }

    fun getSpecies(id: String) {
        apiService.getSpecies(id)
            .enqueue(object : retrofit2.Callback<Species> {
                override fun onFailure(call: Call<Species>, t: Throwable) {
                    Log.d("", "error loading from API getSpecies")
                    species.postValue(null)
                }

                override fun onResponse(
                    call: Call<Species>,
                    response: Response<Species>
                ) {
                    if (response.isSuccessful) {
                        species.postValue(response.body())
                    } else {
                        Log.d("aaa", response.message() + response.code())
                        species.postValue(null)
                    }
                }

            })
    }

    fun getEvolution(id: String) {
        apiService.getEvolution(id)
            .enqueue(object : retrofit2.Callback<Evolution> {
                override fun onFailure(call: Call<Evolution>, t: Throwable) {
                    Log.d("", "error loading from API getSpecies")
                    evoultion.postValue(null)
                }

                override fun onResponse(
                    call: Call<Evolution>,
                    response: Response<Evolution>
                ) {
                    if (response.isSuccessful) {
                        evoultion.postValue(response.body())
                    } else {
                        Log.d("aaa", response.message() + response.code())
                        evoultion.postValue(null)
                    }
                }

            })
    }

    fun setIdPokemon(id: String){
            idPokemon.postValue(id)
    }

    fun setListIdPokemon(listId: MutableList<String>){
        listIdPokemon.postValue(listId)
    }

    fun searchPokemon(query: String, list: MutableList<DetailPokemon>) {
        var listSearch: MutableList<DetailPokemon> = mutableListOf()
        list?.forEachIndexed { index, pokemon ->
            var name = pokemon.name.toString()
            if (query.toUpperCase().equals(name.toUpperCase())) {
                listSearch.add(pokemon)
            }else if(query.equals(pokemon.id.toString())){
                listSearch.add(pokemon)
            }
            searchPokemon.postValue(listSearch)
        }
    }
}