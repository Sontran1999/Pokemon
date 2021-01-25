package com.example.pokemon.views.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.adapter.EvolutionAdapter
import com.example.pokemon.common.Utis
import com.example.pokemon.models.detailpokemon.DetailPokemon
import com.example.pokemon.models.evolution.Evolution
import com.example.pokemon.models.evolution.EvolutionPokemon
import com.example.pokemon.models.pokemons.Pokemon
import com.example.pokemon.models.species.Species
import com.example.pokemon.viewmodel.ViewModelAPI
import kotlinx.android.synthetic.main.fragment_evolutions.*

class EvolutionsFragment(var detailPokemon: DetailPokemon) : Fragment() {
    lateinit var viewModelAPI: ViewModelAPI
    var listVersionPokemon = arrayListOf<String>()
    var listLevelPokemon = arrayListOf<String>()
    var listDetailPokemon: MutableList<DetailPokemon> = arrayListOf()
    private var index = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_evolutions, container, false)

        viewModelAPI = ViewModelAPI()
        registerDetail()
        registerGetVersionPokemon()
        registerGetEvolution(view)
        return view
    }

    private fun showViewEvolution(view: View) {
        val rvEvolutions: RecyclerView = view.findViewById(R.id.rv_Evolution)
        rvEvolutions.setHasFixedSize(true)
        rvEvolutions.layoutManager = LinearLayoutManager(view.context)
        var adapter: EvolutionAdapter = EvolutionAdapter(view.context)
        rvEvolutions.adapter = adapter
        adapter.clear()
        adapter.setList(listDetailPokemon, listLevelPokemon)

    }

    private fun registerGetEvolution(view: View) {
        viewModelAPI.evoultion.observe(this) {
            if (it != null) {
                var idVersionOne: String? = it.chain?.species?.url?.let { it1 -> Utis.cutId(it1) }
                if (idVersionOne != null) {
                    listVersionPokemon.add(idVersionOne)
                }
                var evolution = it.chain?.evolvesTo
                while (evolution?.isNotEmpty() == true) {

                    evolution[0].species?.url?.let { it1 -> Utis.cutId(it1) }?.let { it2 ->
                        listVersionPokemon.add(
                            it2
                        )
                    }

                    listLevelPokemon.add(evolution[0].evolutionDetails?.get(0)?.minLevel.toString())

                    evolution = evolution[0].evolvesTo

                }
                viewModelAPI.setListIdPokemon(listVersionPokemon)

            }
        }

        viewModelAPI.listIdPokemon.observe(this) {
            if (index < it.size) {
                viewModelAPI.getDetailPokemon(it[index])
            } else {
                showViewEvolution(view)
            }
        }
    }

    private fun registerDetail() {
        viewModelAPI.detailPokemon.observe(this) {
            if (it != null) {
                listDetailPokemon.add(it)
                index++
                viewModelAPI.setListIdPokemon(listVersionPokemon)

            }
        }
    }

    private fun registerGetVersionPokemon() {
        viewModelAPI.species.observe(this) {
            if (it != null) {
                var id = it.evolutionChain?.url?.let { it1 -> Utis.cutId(it1) }
                if (id != null) {
                    viewModelAPI.getEvolution(id)
                }
            }
        }
        viewModelAPI.getSpecies(detailPokemon.id.toString())
    }

}