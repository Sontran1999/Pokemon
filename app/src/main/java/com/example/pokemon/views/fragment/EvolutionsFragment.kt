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

class EvolutionsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_evolutions, container, false)
        return view
    }


}