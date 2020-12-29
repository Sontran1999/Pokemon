package com.example.pokemon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.models.detailpokemon.DetailPokemon
import com.example.pokemon.models.pokemons.Pokemon
import com.squareup.picasso.Picasso

class PokemonAdapter(var mContext: Context) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private var mPokemons: MutableList<Pokemon> = mutableListOf()
    private var mDetailPokemon: MutableList<DetailPokemon>? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imagePokemon: ImageView = itemView.findViewById(R.id.imgPokemon)
        var namePokemon: TextView = itemView.findViewById(R.id.tvName)
        var idPokemon: TextView = itemView.findViewById(R.id.tvId)

        fun bindData(data: Pokemon){
            namePokemon.text = data.name
            idPokemon.text = data.id.toString()

            data.detailPokemon?.let { _detail ->
                Picasso.with(imagePokemon.context)
                    .load(_detail.sprites.other.officialArtwork.front_default)
                    .placeholder(R.drawable.pokemon_placeholder)
                    .into(imagePokemon)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.pokemon_items,parent,false
        )
    )

    override fun getItemCount(): Int = mPokemons.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(mPokemons[position])
    }

    fun updatePokemonList(newList: List<Pokemon>){
        val pokemonDataCallback = PokemonDataCallback(newList, mPokemons)
        val pokemonDataResult = DiffUtil.calculateDiff(pokemonDataCallback)

        mPokemons.clear()
        mPokemons.addAll(newList)

        pokemonDataResult.dispatchUpdatesTo(this)
    }

    class PokemonDataCallback(private val newList: List<Pokemon>, private val oldList: MutableList<Pokemon>): DiffUtil.Callback(){
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].id == newList[newItemPosition].id
    }

}