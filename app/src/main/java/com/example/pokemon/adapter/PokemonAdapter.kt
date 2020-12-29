package com.example.pokemon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.models.detailpokemon.DetailPokemon
import com.example.pokemon.models.pokemons.Result
import com.squareup.picasso.Picasso

class PokemonAdapter(var mContext: Context) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private var mResult: MutableList<Result> = arrayListOf()
    private var mDetailPokemon: MutableList<DetailPokemon>? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imagePokemon: ImageView = itemView.findViewById(R.id.imgPokemon)
        var namePokemon: TextView = itemView.findViewById(R.id.tvName)
        var idPokemon: TextView = itemView.findViewById(R.id.tvId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val pokemonView = layoutInflater.inflate(R.layout.pokemon_items,parent,false)
        return ViewHolder(pokemonView)
    }

    override fun getItemCount(): Int {
        return mResult?.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.namePokemon.text = mResult.get(position).name
        if(mDetailPokemon?.size != 0) {
            Picasso.with(mContext)
                .load(mDetailPokemon?.get(position)?.sprites?.other?.officialArtwork?.front_default)
                .placeholder(R.drawable.pokemon_placeholder)
                .into(holder.imagePokemon)
        }

    }

    fun setList1(list: MutableList<Result>){
        this.mResult = list
        notifyDataSetChanged()
    }

    fun setList2(listDetail: MutableList<DetailPokemon>){
        this.mDetailPokemon = listDetail
        notifyDataSetChanged()
    }
}