package com.example.pokemon.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.common.Utis
import com.example.pokemon.models.detailpokemon.DetailPokemon
import com.example.pokemon.models.evolution.Evolution
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class EvolutionAdapter(var mContext: Context) :
    RecyclerView.Adapter<EvolutionAdapter.ViewHolder>() {

    private var mListBefore: MutableList<DetailPokemon> = arrayListOf()
    private var mListAfter: MutableList<DetailPokemon> = arrayListOf()
    private var mListLevel: MutableList<String> = arrayListOf()
    var index1 = 0
    var index2 = 0

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avtAfter: ImageView = itemView.findViewById(R.id.img_afterPokemon)
        val nameAfter: TextView = itemView.findViewById(R.id.tv_afterPokemon)
        val avtBefore: ImageView = itemView.findViewById(R.id.img_pokemonBefore)
        val nameBefore: TextView = itemView.findViewById(R.id.tv_NameOfPokemonBefore)
        val level: TextView = itemView.findViewById(R.id.tv_level)
    }

    fun setList(
        list: MutableList<MutableList<DetailPokemon>>,
        listLevel: MutableList<String>
    ) {
        this.mListBefore = list[0]
        this.mListAfter = list[1]
        this.mListLevel = listLevel
        notifyDataSetChanged()
    }

    fun clear() {
        mListBefore.clear()
        mListAfter.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val homeView: View = layoutInflater.inflate(R.layout.item_evolution, parent, false)
        return ViewHolder(homeView)

    }

    override fun getItemCount(): Int {
        return mListAfter.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (index1 < mListBefore.size) {
            Picasso.with(mContext)
                .load(mListBefore[position].sprites?.other?.officialArtwork?.frontDefault)
                .into(holder.avtBefore)
            holder.nameBefore.text = mListBefore[position].name
            index1++
        } else {
            Picasso.with(mContext)
                .load(mListBefore[position - 1].sprites?.other?.officialArtwork?.frontDefault)
                .into(holder.avtBefore)
            holder.nameBefore.text = mListBefore[position - 1].name
        }
        if (mListLevel.size != 0 && mListLevel[position] != "null") {
            holder.level.text = "Lv. " + mListLevel[position]
        } else {
            holder.level.text = "Lv. updating"
        }

        holder.level.setTextColor(
            mListAfter[position].types?.get(0)?.type?.name?.let { Utis.typeColor(it) }?.let {
                ContextCompat.getColor(
                    mContext,
                    it
                )
            }?.let {
                ColorStateList.valueOf(
                    it
                )
            }
        )
        Picasso.with(mContext)
            .load(mListAfter[position].sprites?.other?.officialArtwork?.frontDefault)
            .into(holder.avtAfter)
        holder.nameAfter.text = mListAfter[position].name

    }

}