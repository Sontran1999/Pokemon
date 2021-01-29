package com.example.pokemontest

import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemon.R
import com.example.pokemon.common.Utis
import com.example.pokemon.models.detailpokemon.DetailPokemon
import com.example.pokemon.models.pokemons.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_stats.*

class StatsFragment(var detailPokemon: DetailPokemon) : Fragment() {
    private var listProgressBar: MutableList<ProgressBar> = arrayListOf()
    private var listTextView: MutableList<TextView> = arrayListOf()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stats, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        val percentMale = (0..100).random()
        val percentFemale = 100 - percentMale

        val pbRate = view.findViewById<ProgressBar>(R.id.pb_CaptureRate)
        val pbGender = view.findViewById<ProgressBar>(R.id.pb_Gender)
        val tvPercentMale = view.findViewById<TextView>(R.id.tv_percentMale)
        val tvPercentFemale = view.findViewById<TextView>(R.id.tv_percentFemale)
        val imgNormal: ImageView = view.findViewById(R.id.img_normal)
        val imgShiny: ImageView = view.findViewById(R.id.img_shiny)
        val tvNumberOfATK: TextView = view.findViewById(R.id.tv_numberOfATK)
        val tvNumberOfDEF: TextView = view.findViewById(R.id.tv_numberOfDEF)
        val tvNumberOfHP: TextView = view.findViewById(R.id.tv_numberOfHP)
        val tvNumberOfSATK: TextView = view.findViewById(R.id.tv_numberOfSATK)
        val tvNumberOfSDEF: TextView = view.findViewById(R.id.tv_numberOfSDEF)
        val tvNumberOfSPD: TextView = view.findViewById(R.id.tv_numberOfSPD)
        val pbHP: ProgressBar = view.findViewById(R.id.pb_HP)
        val pbATK: ProgressBar = view.findViewById(R.id.pb_ATK)
        val pbDEF: ProgressBar = view.findViewById(R.id.pb_DEF)
        val pbSATK: ProgressBar = view.findViewById(R.id.pb_SATK)
        val pbSDEF: ProgressBar = view.findViewById(R.id.pb_SDEF)
        val pbSPD: ProgressBar = view.findViewById(R.id.pb_spd)
        listProgressBar.add(pbATK)
        listProgressBar.add(pbHP)
        listProgressBar.add(pbDEF)
        listProgressBar.add(pbSATK)
        listProgressBar.add(pbSDEF)
        listProgressBar.add(pbSPD)
        listTextView.add(view.findViewById(R.id.tv_Weaknesses))
        listTextView.add(view.findViewById(R.id.tv_Abilities))
        listTextView.add(view.findViewById(R.id.tv_torrent))
        listTextView.add(view.findViewById(R.id.tv_rainDish))
        listTextView.add(view.findViewById(R.id.tv_breeding))
        listTextView.add(view.findViewById(R.id.tv_EggGroup))
        listTextView.add(view.findViewById(R.id.tv_HatchTime))
        listTextView.add(view.findViewById(R.id.tv_Gender))
        listTextView.add(view.findViewById(R.id.tv_Capture))
        listTextView.add(view.findViewById(R.id.tv_Habitat))
        listTextView.add(view.findViewById(R.id.tv_Generation))
        listTextView.add(view.findViewById(R.id.tv_CaptureRate))
        listTextView.add(view.findViewById(R.id.tv_sprites))
        listTextView.add(view.findViewById(R.id.tv_normal))
        listTextView.add(view.findViewById(R.id.tv_Shiny))
        listTextView.add(view.findViewById(R.id.tv_HP))
        listTextView.add(view.findViewById(R.id.tv_ATK))
        listTextView.add(view.findViewById(R.id.tv_DEF))
        listTextView.add(view.findViewById(R.id.tv_SDEF))
        listTextView.add(view.findViewById(R.id.tv_SATK))
        listTextView.add(view.findViewById(R.id.tv_SPD))


        tvPercentFemale.text = "$percentFemale%"
        tvPercentMale.text = "$percentMale%"
        pbGender.progress = percentMale

        val percentRate = (0..100).random()
        val tvRate = view.findViewById<TextView>(R.id.tv_percentCaptureRate)
        tvRate.text = "$percentRate%"
        pbRate.progress = percentRate

        if (detailPokemon.sprites?.other?.officialArtwork?.frontDefault != null) {
            Picasso.with(view.context)
                .load(detailPokemon.sprites?.other?.officialArtwork?.frontDefault)
                .into(imgNormal)
            Picasso.with(view.context)
                .load(detailPokemon.sprites?.other?.officialArtwork?.frontDefault)
                .into(imgShiny)
        }

        tvNumberOfHP.text = detailPokemon.stats?.get(0)?.baseStat.toString()
        tvNumberOfATK.text = detailPokemon.stats?.get(1)?.baseStat.toString()
        tvNumberOfDEF.text = detailPokemon.stats?.get(2)?.baseStat.toString()
        tvNumberOfSATK.text = detailPokemon.stats?.get(3)?.baseStat.toString()
        tvNumberOfSDEF.text = detailPokemon.stats?.get(4)?.baseStat.toString()
        tvNumberOfSPD.text = detailPokemon.stats?.get(5)?.baseStat.toString()

        pbHP.progress = detailPokemon.stats?.get(0)?.baseStat!!
        pbATK.progress = detailPokemon.stats?.get(1)?.baseStat!!
        pbDEF.progress = detailPokemon.stats?.get(2)?.baseStat!!
        pbSATK.progress = detailPokemon.stats?.get(3)?.baseStat!!
        pbSDEF.progress = detailPokemon.stats?.get(4)?.baseStat!!
        pbSPD.progress = detailPokemon.stats?.get(5)?.baseStat!!


        listProgressBar.forEachIndexed { index, progressBar ->
            progressBar.progressTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    view.context,
                    Utis.typeColor(detailPokemon.types?.get(0)?.type?.name.toString())
                )
            )
        }

        listTextView.forEachIndexed { index, textView ->
            textView.setTextColor(
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        view.context,
                        Utis.typeColor(detailPokemon.types?.get(0)?.type?.name.toString())
                    )
                )
            )
        }
    }

}