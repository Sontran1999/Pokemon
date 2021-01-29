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
import com.example.pokemon.common.Utis
import com.example.pokemon.models.detailpokemon.Move
import com.example.pokemon.models.detailpokemon.Moves

class MoveAdapter (var mContext: Context) : RecyclerView.Adapter<MoveAdapter.ViewHolder>() {

    private var list = ArrayList<Move>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgTypePokemon: ImageView = itemView.findViewById(R.id.img_typeOfPokemon)
        val name: TextView = itemView.findViewById(R.id.tv_nameOfMove)
        val level: TextView = itemView.findViewById(R.id.tv_levelLearn)
    }
    fun  setList(list : ArrayList<Move>){
        this.list = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val homeView: View = layoutInflater.inflate(R.layout.item_move, parent, false)
        return ViewHolder(homeView)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imgTypePokemon.setImageResource(R.drawable.ic_normal)
        holder.name.text = list[position].move?.name?.let { Utis.toUpperCase(it) }
        holder.level.text = "Level " + list[position].versionGroupDetails?.get(0)?.levelLearnedAt.toString()
    }

}