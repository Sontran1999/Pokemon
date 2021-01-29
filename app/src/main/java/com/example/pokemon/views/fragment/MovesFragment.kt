package com.example.pokemontest

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.adapter.MoveAdapter
import com.example.pokemon.models.detailpokemon.DetailPokemon
import com.example.pokemon.models.detailpokemon.Move
import com.example.pokemon.viewmodel.ViewModelAPI
import kotlinx.android.synthetic.main.fragment_moves.*

class MovesFragment(var detailPokemon: DetailPokemon) : Fragment() {
    lateinit var viewModelAPI: ViewModelAPI
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_moves, container, false)
        viewModelAPI = ViewModelAPI()

        showViewMove(view)
        return view
    }

    private fun showViewMove(view: View) {
        var rvMoves: RecyclerView = view.findViewById(R.id.rv_Moves)
        rvMoves.setHasFixedSize(true)
        rvMoves.layoutManager = LinearLayoutManager(view.context)
        var adapter: MoveAdapter = MoveAdapter(view.context)
        rvMoves.adapter = adapter
        adapter.setList(detailPokemon.moves as ArrayList<Move>)

    }
}