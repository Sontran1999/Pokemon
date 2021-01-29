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
    var listBeforePokemon = arrayListOf<String>()
    var listAfterPokemon = arrayListOf<String>()
    var listLevelPokemon = arrayListOf<String>()
    var listDetailPokemon: MutableList<DetailPokemon> = arrayListOf()
    private var listAllVersionPokemon = arrayListOf<ArrayList<String>>()
    private var listAllDetailPokemon: MutableList<MutableList<DetailPokemon>> = arrayListOf()
    private var index = 0
    var index2 = 0

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
        viewModelAPI.getSpecies(detailPokemon.id.toString())
        return view
    }

    private fun showViewEvolution(view: View) {
        val rvEvolutions: RecyclerView = view.findViewById(R.id.rv_Evolution)
        rvEvolutions.setHasFixedSize(true)
        rvEvolutions.layoutManager = LinearLayoutManager(view.context)
        var adapter: EvolutionAdapter = EvolutionAdapter(view.context)
        rvEvolutions.adapter = adapter
        adapter.clear()
        adapter.setList(listAllDetailPokemon, listLevelPokemon)

    }

    private fun registerGetEvolution(view: View) {
        var count = 0
        var count2 = 0
        viewModelAPI.evoultion.observe(this) {
            if (it != null) {
                var evolution = it.chain?.evolvesTo
                if (it.chain?.evolvesTo?.isNotEmpty() == true) {
                    while (count < it.chain?.evolvesTo?.size!!) {
                        var idBeforeEvolution: String? =
                            it.chain?.species?.url?.let { it1 -> Utis.cutId(it1) }
                        if (idBeforeEvolution != null) {
                            listBeforePokemon.add(idBeforeEvolution)
                        }
                        while (count2 < evolution?.size!!) {
                                evolution[count2].species?.url?.let { it1 -> Utis.cutId(it1) }
                                    ?.let { it2 ->
                                        listAfterPokemon.add(it2)
                                    }

                                listLevelPokemon.add(evolution[count2].evolutionDetails?.get(0)?.minLevel.toString())
                                if (evolution[count2].evolvesTo?.isNotEmpty() == true) {
                                    evolution[count2].species?.url?.let { it1 -> Utis.cutId(it1) }
                                        ?.let { it2 ->
                                            listBeforePokemon.add(it2)
                                        }
                                    evolution = evolution[count2].evolvesTo
                                } else {
                                    count2++
                                }
                        }
                        count++
                    }
                }
                listAllVersionPokemon.add(listBeforePokemon)
                listAllVersionPokemon.add(listAfterPokemon)
                viewModelAPI.setListIdPokemon(listAllVersionPokemon)
            }
        }

        viewModelAPI.listIdPokemon.observe(this) {
            if (index < it.size) {
                if (index2 < it[index].size) {
                    viewModelAPI.getDetailPokemon(it[index][index2])
                } else {
                    listAllDetailPokemon.add(ArrayList(listDetailPokemon))
                    index++
                    index2 = 0
                    viewModelAPI.setListIdPokemon(listAllVersionPokemon)
                }
            } else {
                showViewEvolution(view)
            }
        }
    }

    private fun registerDetail() {
        viewModelAPI.detailPokemon.observe(this) {
            if (it != null) {
                if(index2 == 0){
                    listDetailPokemon.clear()
                }
                listDetailPokemon.add(it)
                index2++
                viewModelAPI.setListIdPokemon(listAllVersionPokemon)

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
    }

}