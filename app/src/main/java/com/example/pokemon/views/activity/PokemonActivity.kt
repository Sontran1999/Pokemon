package com.example.pokemon.views.activity

import android.os.AsyncTask
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemon.R
import com.example.pokemon.adapter.PokemonAdapter
import com.example.pokemon.models.detailpokemon.DetailPokemon
import com.example.pokemon.models.pokemons.Pokemons
import com.example.pokemon.models.pokemons.Result
import com.example.pokemon.viewmodel.ViewModelAPI
import kotlinx.android.synthetic.main.activity_pokemon.*
import kotlinx.coroutines.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.concurrent.thread
import kotlin.coroutines.CoroutineContext


class PokemonActivity : AppCompatActivity(), CoroutineScope {
    var adapter: PokemonAdapter? = null
    lateinit var viewModelAPI: ViewModelAPI
    var listId: MutableList<String> = arrayListOf()
    var listDetail: MutableList<DetailPokemon> = arrayListOf()
    var listPokemon: MutableList<Result> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)
        viewModelAPI = ViewModelAPI()
        adapter = PokemonAdapter(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        setActionBar()
        loadView()
        Toast.makeText(this, listId.size.toString(), Toast.LENGTH_SHORT).show()


    }

    private fun setActionBar() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    private fun loadView() {
        viewModelAPI.pokemon.observe(this) {
            if (it != null) {
                it.results?.forEachIndexed { index, result ->
                    var count = 0
                    var n = result.url?.length?.minus(2)
                    var item = result.url
                    if (n != null) {
                        for (i in n downTo 0) {
                            if (item?.get(i) == '/') {
                                count = i
                                break
                            }
                        }
                        var id = item?.substring(count + 1, n + 1)
                        id?.let { it1 -> listId.add(it1) }
                    }
                }
                listPokemon = it.results as MutableList<Result>
                adapter?.setList1(listPokemon)
                loadView2()
            } else {
                Toast.makeText(this, "ERROR API", Toast.LENGTH_SHORT).show()
            }
        }
        viewModelAPI.getAllPokemon()
//        listPokemon.clear()
//        listId.clear()
    }

    private fun loadView2() {
            listId.forEachIndexed { index, s ->
                    viewModelAPI.detailPokemon.observe(this) { it1: DetailPokemon ->
                        if (it1 != null) {
                            listDetail.add(it1)
                            adapter?.setList2(listDetail)
                        }
                        Toast.makeText(this, listDetail.size.toString(), Toast.LENGTH_SHORT).show()
                    }
                    viewModelAPI.getDetailPokemon(s)
            }

    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}