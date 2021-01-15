package com.example.pokemon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R

class EvolutionAdapter(var mContext: Context) : RecyclerView.Adapter<EvolutionAdapter.ViewHolder>() {

    private var list = ArrayList<Evolution>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avtAfter: ImageView = itemView.findViewById(R.id.img_afterPokemon)
        val nameAfter: TextView = itemView.findViewById(R.id.tv_afterPokemon)
        val avtBefore: ImageView = itemView.findViewById(R.id.img_pokemonBefore)
        val nameBefore: TextView = itemView.findViewById(R.id.tv_NameOfPokemonBefore)
        val level: TextView = itemView.findViewById(R.id.tv_level)
    }
    fun  setList(list : ArrayList<Evolution>){
        this.list = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val homeView: View = layoutInflater.inflate(R.layout.item_evolution, parent, false)
        return ViewHolder(homeView)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.avtBefore.setImageResource(list[position].avtPokemonBefore)
        holder.avtAfter.setImageResource(list[position].avtPokemonAfter)
        holder.nameAfter.text = list[position].namePokemonAfter
        holder.nameBefore.text = list[position].namePokemonBefore
        holder.level.text = list[position].levelUp
    }

}