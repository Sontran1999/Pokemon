package com.example.pokemon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.common.Utis
import com.example.pokemon.models.detailpokemon.Types
import com.squareup.picasso.Picasso

class TypeAdapter: RecyclerView.Adapter<TypeAdapter.ViewHoder>() {
    var mType: MutableList<Types> = arrayListOf()

    class ViewHoder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var type: ImageView = itemView.findViewById(R.id.img_Type)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeAdapter.ViewHoder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.item_type, parent, false)
        var viewHoder: ViewHoder = ViewHoder(view)
        return viewHoder
    }

    override fun onBindViewHolder(holder: TypeAdapter.ViewHoder, position: Int) {
       holder.type.setImageResource(
           Utis.typePokemon(mType[position].type.name)
       )
    }

    override fun getItemCount(): Int {
        return mType.size
    }

    fun setList(list: MutableList<Types>?){
        if (list != null) {
            this.mType = list
        }
        notifyDataSetChanged()
    }
}