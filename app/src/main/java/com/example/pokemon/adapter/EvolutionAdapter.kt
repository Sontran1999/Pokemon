package com.example.pokemon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.models.detailpokemon.DetailPokemon
import com.example.pokemon.models.evolution.Evolution
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class EvolutionAdapter(var mContext: Context) : RecyclerView.Adapter<EvolutionAdapter.ViewHolder>() {

    private var mList: MutableList<DetailPokemon> = arrayListOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avtAfter: ImageView = itemView.findViewById(R.id.img_afterPokemon)
        val nameAfter: TextView = itemView.findViewById(R.id.tv_afterPokemon)
        val avtBefore: ImageView = itemView.findViewById(R.id.img_pokemonBefore)
        val nameBefore: TextView = itemView.findViewById(R.id.tv_NameOfPokemonBefore)
        val level: TextView = itemView.findViewById(R.id.tv_level)
    }
    fun  setList(list : MutableList<DetailPokemon>){
        this.mList = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val homeView: View = layoutInflater.inflate(R.layout.item_evolution, parent, false)
        return ViewHolder(homeView)

    }

    override fun getItemCount(): Int {
        return if( mList.size - 1 > 0){
            mList.size -1
        } else mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.with(mContext)
            .load(mList[position]?.sprites?.other?.officialArtwork?.front_default)
            .into(holder.avtBefore)
        holder.nameBefore.text = mList[position].name
        if(mList.size - 1 > 0){
            Picasso.with(mContext)
                .load(mList[position]?.sprites?.other?.officialArtwork?.front_default)
                .into(holder.avtAfter)
            holder.nameAfter.text = mList[position].name
        }
        else{
            Picasso.with(mContext)
                .load(mList[position+1]?.sprites?.other?.officialArtwork?.front_default)
                .into(holder.avtAfter)
            holder.nameAfter.text = mList[position+1].name
        }
    }

}