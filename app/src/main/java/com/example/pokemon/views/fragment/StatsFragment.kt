package com.example.pokemontest

import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemon.R
import com.example.pokemon.models.pokemons.Pokemon
import kotlinx.android.synthetic.main.fragment_stats.*

class StatsFragment: Fragment() {


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_stats, container, false)
        init(view)
        return view
    }

    private fun init(view: View){
        val percentMale = (0..100).random()
        val percentFemale = 100 - percentMale
        val pbGender = view.findViewById<ProgressBar>(R.id.pb_Gender)
        val tvPercentMale = view.findViewById<TextView>(R.id.tv_percentMale)
        val tvPercentFemale = view.findViewById<TextView>(R.id.tv_percentFemale)
        tvPercentFemale.text = "$percentFemale%"
        tvPercentMale.text = "$percentMale%"
        pbGender.progress = percentMale

        val percentRate = (0..100).random()
        val pbRate = view.findViewById<ProgressBar>(R.id.pb_CaptureRate)
        val tvRate = view.findViewById<TextView>(R.id.tv_percentCaptureRate)
        tvRate.text = "$percentRate%"
        pbRate.progress = percentRate
    }
}