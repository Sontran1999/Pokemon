package com.example.pokemon.views.activity

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemon.R
import com.example.pokemon.adapter.EvolutionAdapter
import com.example.pokemon.common.Utis
import com.example.pokemon.models.detailpokemon.DetailPokemon
import com.example.pokemon.models.evolution.Evolution
import com.example.pokemon.models.evolution.EvolutionPokemon
import com.example.pokemon.models.species.Species
import com.example.pokemon.models.pokemons.Pokemon
import com.example.pokemon.viewmodel.ViewModelAPI
import com.example.pokemon.views.fragment.EvolutionsFragment
import com.example.pokemontest.MovesFragment
import com.example.pokemontest.StatsFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_evolutions.*
import kotlinx.android.synthetic.main.fragment_stats.*
import java.io.Serializable

class DetailActivity : AppCompatActivity(), View.OnClickListener, Serializable {
    private val listFragment = arrayListOf(StatsFragment(), EvolutionsFragment(), MovesFragment())
    private val fragmentManager: FragmentManager = supportFragmentManager
    private val fragmentTransaction = fragmentManager.beginTransaction()
    private var count = 0
    var pokemon: Pokemon? = null
    private var detailPokemon: DetailPokemon? = null
    lateinit var viewModelAPI: ViewModelAPI
    private lateinit var species: Species
    var bundle: Bundle? = null
    lateinit var evolution: Evolution
    var listDetailPokemon: MutableList<DetailPokemon> = arrayListOf()
    var listIdVersion: MutableList<String> = arrayListOf()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var bundle = intent.getBundleExtra("data")
        pokemon = bundle?.getSerializable("object") as Pokemon
        viewModelAPI = ViewModelAPI()

        getDetail()
        setActionBar()
        getVersionPokemon()

        listFragment.forEachIndexed { _, fragment ->
            fragmentTransaction.add(R.id.fm_content, fragment)
            fragmentTransaction.hide(fragment)
        }
        fragmentTransaction.show(listFragment[0])
        fragmentTransaction.commit()
    }

    private fun setActionBar() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        )
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun init() {
        tv_nameOfPokemon.visibility = View.INVISIBLE
        btn_HideOrShowDetail.setOnClickListener(this)
        cv_Moves.setOnClickListener(this)
        cv_Evolutions.setOnClickListener(this)
        cv_Stats.setOnClickListener(this)
        Picasso.with(this)
            .load(detailPokemon?.sprites?.other?.officialArtwork?.front_default)
            .into(img_avatar)
        tv_Name.text = detailPokemon?.name?.let { Utis.toUpperCase(it) }
        Picasso.with(this)
            .load(detailPokemon?.sprites?.other?.officialArtwork?.front_default)
            .into(img_normal)
        Picasso.with(this)
            .load(detailPokemon?.sprites?.other?.officialArtwork?.front_default)
            .into(img_shiny)

        tv_numberOfHP.text = detailPokemon?.stats?.get(0)?.base_stat.toString()
        tv_numberOfATK.text = detailPokemon?.stats?.get(1)?.base_stat.toString()
        tv_numberOfDEF.text = detailPokemon?.stats?.get(2)?.base_stat.toString()
        tv_numberOfSATK.text = detailPokemon?.stats?.get(3)?.base_stat.toString()
        tv_numberOfSDEF.text = detailPokemon?.stats?.get(4)?.base_stat.toString()
        tv_numberOfSPD.text = detailPokemon?.stats?.get(5)?.base_stat.toString()

        pb_HP.progress = detailPokemon?.stats?.get(0)?.base_stat!!
        pb_ATK.progress = detailPokemon?.stats?.get(1)?.base_stat!!
        pb_DEF.progress = detailPokemon?.stats?.get(2)?.base_stat!!
        pb_SATK.progress = detailPokemon?.stats?.get(3)?.base_stat!!
        pb_SDEF.progress = detailPokemon?.stats?.get(4)?.base_stat!!
        pb_spd.progress = detailPokemon?.stats?.get(5)?.base_stat!!

        setColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    this,
                    Utis.typeColor(detailPokemon?.types?.get(0)?.type?.name.toString())
                )
            )
        )


    }

    private fun bindTypePokemonLgSrc(imv: ImageView, type: String) {
        Picasso.with(imv.context)
            .load(Utis.typeLG(type))
            .into(imv)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun getDetail() {
        viewModelAPI.detailPokemon.observe(this) {
            if (it != null) {
                detailPokemon = it
                init()
            }
        }
        pokemon?.name?.let { viewModelAPI.getDetailPokemon(it) }
    }

    private fun getVersionPokemon() {
        viewModelAPI.species.observe(this) {
            if (it != null) {
                species = it
                var count = 0
                var n = species?.evolutionChain?.url?.length?.minus(2)
                var item = species?.evolutionChain?.url
                if (n != null) {
                    for (i in n downTo 0) {
                        if (item!![i] == '/') {
                            count = i
                            break
                        }
                    }
                    item?.substring(count + 1, n + 1)?.let { getEvolution(it) }
                }
            }
        }
        viewModelAPI.getSpecies(pokemon?.id.toString())
    }

    private fun getEvolution(id: String) {
        viewModelAPI.evoultion.observe(this) {
            if (it != null) {
                evolution = it
                var idVersionOne: String? = it.chain?.species?.url?.let { it1 -> Utis.cutId(it1) }
                var idVersionTwo: String? =
                    it.chain?.evolvesTo?.get(0)?.species?.url?.let { it1 ->
                        Utis.cutId(it1)
                    }
                getPicEvolution(idVersionOne)
                if (idVersionTwo != null) {
                    getPicEvolution(idVersionTwo)
                }
                var idVersionThree: String? =
                    it.chain?.evolvesTo?.get(0)?.evolvesTo?.get(0)?.species?.url?.let { it1 ->
                        Utis.cutId(
                            it1
                        )
                    }
                getPicEvolution(idVersionThree)
                showView()
            }
        }
        viewModelAPI.getEvolution(id)
    }

    private fun getPicEvolution(id: String?) {
        viewModelAPI.versionPokemon.observe(this) {
            if (it != null) {
                listDetailPokemon.add(it)
            }
        }
        if (id != null) {
            viewModelAPI.getVersionPokemon(id)
        }

    }

    private fun showView() {
        rv_Evolution.setHasFixedSize(true)
        rv_Evolution.layoutManager = LinearLayoutManager(this)
        var adapter: EvolutionAdapter = EvolutionAdapter(this)
        adapter.setList(listDetailPokemon)
        rv_Evolution.adapter = adapter
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setColor(color: ColorStateList) {
        tv_Weaknesses.setTextColor(color)
        tv_Abilities.setTextColor(color)
        tv_torrent.setTextColor(color)
        tv_rainDish.setTextColor(color)
        tv_breeding.setTextColor(color)
        tv_EggGroup.setTextColor(color)
        tv_HatchTime.setTextColor(color)
        tv_Gender.setTextColor(color)
        tv_Capture.setTextColor(color)
        tv_Habitat.setTextColor(color)
        tv_Generation.setTextColor(color)
        tv_CaptureRate.setTextColor(color)
        tv_sprites.setTextColor(color)
        tv_normal.setTextColor(color)
        tv_Shiny.setTextColor(color)

        ctl_View.setBackgroundResource(Utis.typeColor(detailPokemon?.types?.get(0)?.type?.name.toString()))

        bindTypePokemonLgSrc(img_tag, detailPokemon?.types?.get(0)?.type?.name.toString())

        cv_Stats.setCardBackgroundColor(color)
        tv_moves.setTextColor(color)
        tv_evolutions.setTextColor(color)
        pb_HP.progressTintList = color
        pb_ATK.progressTintList = color
        pb_DEF.progressTintList = color
        pb_SATK.progressTintList = color
        pb_SDEF.progressTintList = color
        pb_spd.progressTintList = color
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_HideOrShowDetail -> {
                count++
                if (count % 2 == 1) {
                    img_avatar.visibility = View.GONE
                    img_tag.visibility = View.GONE
                    tv_Name.visibility = View.GONE
                    tv_description.visibility = View.GONE
                    tv_topOfInformation.visibility = View.GONE
                    tv_nameOfPokemon.visibility = View.VISIBLE
                } else {
                    img_avatar.visibility = View.VISIBLE
                    img_tag.visibility = View.VISIBLE
                    tv_Name.visibility = View.VISIBLE
                    tv_description.visibility = View.VISIBLE
                    tv_topOfInformation.visibility = View.VISIBLE
                    tv_nameOfPokemon.visibility = View.GONE
                }

            }
            R.id.cv_Stats -> {
                showStats()
            }
            R.id.cv_Evolutions -> {
                showEvolution()
            }
            R.id.cv_Moves -> {
                showMoves()
            }
        }
    }

    private fun showStats() {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        listFragment.forEachIndexed { _, fragment ->
            fragmentTransaction.hide(fragment)
        }
        fragmentTransaction.show(listFragment[0])
        fragmentTransaction.commit()
        cv_Stats.setCardBackgroundColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    this,
                    Utis.typeColor(detailPokemon?.types?.get(0)?.type?.name.toString())
                )
            )
        )
        tv_Stats.setTextColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    this,
                    R.color.white
                )
            )
        )
        cv_Evolutions.setCardBackgroundColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    this,
                    R.color.bg_tab_bar
                )
            )
        )
        tv_evolutions.setTextColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    this,
                    Utis.typeColor(detailPokemon?.types?.get(0)?.type?.name.toString())
                )
            )
        )
        cv_Moves.setCardBackgroundColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    this,
                    R.color.bg_tab_bar
                )
            )
        )
        tv_moves.setTextColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    this,
                    Utis.typeColor(detailPokemon?.types?.get(0)?.type?.name.toString())
                )
            )
        )
    }

    private fun showEvolution() {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        listFragment.forEachIndexed { _, fragment ->
            fragmentTransaction.hide(fragment)
        }
        fragmentTransaction.show(listFragment[1])
        fragmentTransaction.commit()
        cv_Evolutions.setCardBackgroundColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    this,
                    Utis.typeColor(detailPokemon?.types?.get(0)?.type?.name.toString())
                )
            )
        )
        tv_evolutions.setTextColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    this,
                    R.color.white
                )
            )
        )
        cv_Stats.setCardBackgroundColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    this,
                    R.color.bg_tab_bar
                )
            )
        )
        tv_Stats.setTextColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    this,
                    Utis.typeColor(detailPokemon?.types?.get(0)?.type?.name.toString())
                )
            )
        )
        cv_Moves.setCardBackgroundColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    this,
                    R.color.bg_tab_bar
                )
            )
        )
        tv_moves.setTextColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    this,
                    Utis.typeColor(detailPokemon?.types?.get(0)?.type?.name.toString())
                )
            )
        )
    }

    private fun showMoves() {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        listFragment.forEachIndexed { _, fragment ->
            fragmentTransaction.hide(fragment)
        }
        fragmentTransaction.show(listFragment[2])
        fragmentTransaction.commit()
        cv_Moves.setCardBackgroundColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    this,
                    Utis.typeColor(detailPokemon?.types?.get(0)?.type?.name.toString())
                )
            )
        )
        tv_moves.setTextColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    this,
                    R.color.white
                )
            )
        )
        cv_Evolutions.setCardBackgroundColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    this,
                    R.color.bg_tab_bar
                )
            )
        )
        tv_evolutions.setTextColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    this,
                    Utis.typeColor(detailPokemon?.types?.get(0)?.type?.name.toString())
                )
            )
        )
        cv_Stats.setCardBackgroundColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    this,
                    R.color.bg_tab_bar
                )
            )
        )
        tv_Stats.setTextColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    this,
                    Utis.typeColor(detailPokemon?.types?.get(0)?.type?.name.toString())
                )
            )
        )
    }
}