package com.example.pokemon.api

import com.example.pokemon.api.RetrofitClient

class ApiUtils {

    private val BASE_URL = "https://pokeapi.co/api/v2/"

    fun getAPIService(): APIService {
        return RetrofitClient.getClient(BASE_URL)!!.create(APIService::class.java)
    }
}