package com.example.pokemon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.textclassifier.TextClassificationContext
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.common.Utis
import com.example.pokemon.models.detailpokemon.DetailPokemon
import com.example.pokemon.models.detailpokemon.Moves
import com.example.pokemon.models.detailpokemon.Types
import com.example.pokemon.models.pokemons.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_moves.*


class PokemonAdapter(
    var context: Context,
    private val onClick: (Pokemon) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mPokemon: MutableList<Pokemon> = mutableListOf()
    private var mDetailPokemon: MutableList<DetailPokemon> = arrayListOf()
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imagePokemon: ImageView = itemView.findViewById(R.id.imgPokemon)
        var namePokemon: TextView = itemView.findViewById(R.id.tvName)
        var idPokemon: TextView = itemView.findViewById(R.id.tvId)
        var constrain: ConstraintLayout = itemView.findViewById(R.id.constrainLayout)
        var recyclerView: RecyclerView = itemView.findViewById(R.id.rv_Type)

        fun bindData(data: Pokemon) {
            namePokemon.text = data.name?.let { Utis.toUpperCase(it) }
            idPokemon.text = "#"+data.id.toString()
            Picasso.with(itemView.context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + data.id.toString() + ".png")
                .placeholder(R.drawable.pokemon_placeholder)
                .into(imagePokemon)
//            recyclerView.setHasFixedSize(true)
//            recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
//            var adapter: TypeAdapter = TypeAdapter()
//            recyclerView.adapter = adapter
//            adapter.setList(data.detailPokemon?.types as MutableList<Types>)

        }
//            data.detailPokemon?.let { _detail ->
//                Picasso.with(imagePokemon.context)
//                    .load(_detail.sprites.other.officialArtwork.front_default)
//                    .placeholder(R.drawable.pokemon_placeholder)
//                    .into(imagePokemon)
//            }
//        fun bindData2(mDetailPokemon: DetailPokemon) {
//            namePokemon.text = mDetailPokemon.name
//            idPokemon.text = mDetailPokemon.id.toString()
//            Picasso.with(itemView.context)
//                .load(mDetailPokemon.sprites.other.officialArtwork.front_default)
//                .placeholder(R.drawable.pokemon_placeholder)
//                .into(imagePokemon)
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            val view: View =
                LayoutInflater.from(parent.context).inflate(R.layout.pokemon_items, parent, false)
            ViewHolder(view)
        } else {
            val view: View =
                LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
            LoadingViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return mPokemon.size

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.bindData(mPokemon[position])
            holder.constrain.setOnClickListener {
                onClick(mPokemon[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (mPokemon[position].type.equals("null")) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    fun updatePokemonList(newList: List<Pokemon>) {
        val pokemonDataCallback = PokemonDataCallback(newList, mPokemon)
        val pokemonDataResult = DiffUtil.calculateDiff(pokemonDataCallback)

        mPokemon.clear()
        mPokemon.addAll(newList)
        notifyDataSetChanged()
        pokemonDataResult.dispatchUpdatesTo(this)
    }

    class PokemonDataCallback(
        private val newList: List<Pokemon>,
        private val oldList: MutableList<Pokemon>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    fun setListPokemon(list: MutableList<Pokemon>) {
        this.mPokemon = list
        mPokemon.add(Pokemon("null"))
        notifyItemInserted(mPokemon.size - 1)
    }

    fun removePokemon(list: MutableList<Pokemon>) {
        this.mPokemon = list
        this.mPokemon.removeAt(mPokemon.size - 1)
        notifyItemRemoved(mPokemon.size)
    }

    fun addPokemon(list: MutableList<Pokemon>) {
        mPokemon.addAll(list)
        notifyDataSetChanged()
    }

    fun reset() {
        mPokemon.clear()
        notifyDataSetChanged()
    }

    fun setPokemon(list: MutableList<Pokemon>){
        this.mPokemon = list
        mPokemon.clear()
        notifyDataSetChanged()
    }

}