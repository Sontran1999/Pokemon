package com.example.pokemon.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.models.pokemons.Pokemons
import com.example.pokemon.api.APIService
import com.example.pokemon.api.ApiUtils
import com.example.pokemon.models.detailpokemon.DetailPokemon
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class ViewModelAPI : ViewModel() {
//    var context = getApplication<Context>().applicationContext
    var pokemon: MutableLiveData<Pokemons> = MutableLiveData()
    var detailPokemon: MutableLiveData<DetailPokemon> = MutableLiveData()
    var apiService: APIService = ApiUtils().getAPIService()

    fun getAllPokemon() {
        val call = apiService.getPokemon()
        call.enqueue(object : retrofit2.Callback<Pokemons> {
            override fun onFailure(call: Call<Pokemons>, t: Throwable) {
                Log.d("pokemon", "error loading from ApPI")
                pokemon.postValue(null)
            }

            override fun onResponse(call: Call<Pokemons>, response: Response<Pokemons>) {
                if (response.isSuccessful) {
                    Log.d("pokemon", "pokemon loaded from ApPI")
                    pokemon.postValue(response.body())
                } else {
                    Log.d("pokemon", response.message() + response.code())
                    pokemon.postValue(null)
                }
            }

        })
    }

    fun getDetailPokemon(id: String) {
        val call = apiService.getDetailPokemon(id)
        call.enqueue(object : retrofit2.Callback<DetailPokemon> {
            override fun onFailure(call: Call<DetailPokemon>, t: Throwable) {
                Log.d("", "error loading from ApPI")
                detailPokemon.postValue(null)
            }

            override fun onResponse(
                call: Call<DetailPokemon>,
                response: Response<DetailPokemon>
            ) {
                if (response.isSuccessful) {
                    Log.d("aaa", "pokemon loaded from ApPI")
                    detailPokemon.postValue(response.body())
                } else {
                    Log.d("aaa", response.message() + response.code())
                    detailPokemon.postValue(null)
                }
            }

        })

    }
}