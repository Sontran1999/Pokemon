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
import com.example.pokemon.models.detailpokemon.Type
import com.example.pokemon.models.detailpokemon.Types
import com.example.pokemon.models.pokemons.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_moves.*
import kotlinx.android.synthetic.main.item_loading.view.*


class PokemonAdapter(
    var context: Context,
    private val onClick: (DetailPokemon) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mDetailPokemon: MutableList<DetailPokemon> = arrayListOf()

    var isLoading = false

    companion object{
        const val VIEW_TYPE_ITEM = 0
        const val VIEW_TYPE_LOADING = 1
    }

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imagePokemon: ImageView = itemView.findViewById(R.id.imgPokemon)
        var namePokemon: TextView = itemView.findViewById(R.id.tvName)
        var idPokemon: TextView = itemView.findViewById(R.id.tvId)
        var constrain: ConstraintLayout = itemView.findViewById(R.id.constrainLayout)
        var recyclerView: RecyclerView = itemView.findViewById(R.id.rv_Type)

        fun bindData(data: DetailPokemon) {
            namePokemon.text = data.name?.let { Utis.toUpperCase(it) }
            idPokemon.text = "#" + data.id.toString()
            Picasso.with(imagePokemon.context)
                .load(data.sprites?.other?.officialArtwork?.frontDefault)
                .placeholder(R.drawable.pokemon_placeholder)
                .into(imagePokemon)
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            var adapter: TypeAdapter = TypeAdapter()
            recyclerView.adapter = adapter
            adapter.setList(data.types as MutableList<Type>)

        }
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
        return mDetailPokemon.size

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.bindData(mDetailPokemon[position])
            holder.constrain.setOnClickListener {
                onClick(mDetailPokemon[position])
            }
        }else{
            holder.itemView.progressBar.visibility = View.VISIBLE
        }
    }

    override fun getItemViewType(position: Int): Int {
        return mDetailPokemon[position].itemType
    }

    fun updatePokemonList(list: MutableList<DetailPokemon>) {
        this.mDetailPokemon = ArrayList(list)
        setLoadMoreItem(false)
        notifyDataSetChanged()
    }

    fun setLoadMoreItem(isLoadMore: Boolean) {
        this.isLoading = isLoadMore
        if (isLoadMore) {
            mDetailPokemon.add(DetailPokemon(VIEW_TYPE_LOADING))
        } else {
            mDetailPokemon.removeAll { it.itemType == VIEW_TYPE_LOADING }
        }
        notifyItemChanged(mDetailPokemon.lastIndex)
    }

    fun reset() {
        mDetailPokemon.clear()
        notifyDataSetChanged()
    }

}