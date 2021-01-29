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
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemon.R
import com.example.pokemon.adapter.EvolutionAdapter
import com.example.pokemon.adapter.MoveAdapter
import com.example.pokemon.common.Utis
import com.example.pokemon.models.detailpokemon.DetailPokemon
import com.example.pokemon.models.detailpokemon.Move
import com.example.pokemon.models.detailpokemon.Moves
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
import kotlinx.android.synthetic.main.fragment_moves.*
import kotlinx.android.synthetic.main.fragment_stats.*
import java.io.Serializable

class DetailActivity : AppCompatActivity(), View.OnClickListener, Serializable {
    private val listFragment = arrayListOf<Fragment>()
    private val fragmentManager: FragmentManager = supportFragmentManager
    private val fragmentTransaction = fragmentManager.beginTransaction()
    private var count = 0
    private lateinit var detailPokemon: DetailPokemon
    lateinit var viewModelAPI: ViewModelAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        viewModelAPI = ViewModelAPI()

        val bundle = intent.getBundleExtra("data")
        if (bundle != null) {
            detailPokemon = bundle.getSerializable("object") as DetailPokemon
        }

        setActionBar()
        registerObserve()
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

    private fun initFragment() {
        listFragment.add(StatsFragment(detailPokemon))
        listFragment.add(EvolutionsFragment(detailPokemon))
        listFragment.add(MovesFragment(detailPokemon))

        listFragment.forEachIndexed { _, fragment ->
            fragmentTransaction.add(R.id.fm_content, fragment)
            fragmentTransaction.hide(fragment)
        }
        fragmentTransaction.show(listFragment[0])
        fragmentTransaction.commit()
    }

    private fun init() {

        tv_nameOfPokemon.visibility = View.INVISIBLE
        btn_HideOrShowDetail.setOnClickListener(this)
        cv_Moves.setOnClickListener(this)
        cv_Evolutions.setOnClickListener(this)
        cv_Stats.setOnClickListener(this)

        if (detailPokemon.sprites?.other?.officialArtwork?.frontDefault != null) {
            Picasso.with(this)
                .load(detailPokemon.sprites?.other?.officialArtwork?.frontDefault)
                .into(img_avatar)
            tv_Name.text = detailPokemon.name?.let { Utis.toUpperCase(it) }
        }

        setColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    this,
                    Utis.typeColor(detailPokemon.types?.get(0)?.type?.name.toString())
                )
            )
        )

    }

    private fun bindTypePokemonLgSrc(imv: ImageView, type: String) {
        Picasso.with(imv.context)
            .load(Utis.typeLG(type))
            .into(imv)
    }

    private fun registerObserve() {
        getDetail()
    }

    private fun getDetail() {
        viewModelAPI.detailPokemon.observe(this) {
            if (it != null) {
                detailPokemon = it
                initFragment()
                init()
            }
        }
        viewModelAPI.getDetailPokemon(detailPokemon.id.toString())
    }

    private fun setColor(color: ColorStateList) {
        ctl_View.setBackgroundResource(Utis.typeColor(detailPokemon?.types?.get(0)?.type?.name.toString()))

        bindTypePokemonLgSrc(img_tag, detailPokemon?.types?.get(0)?.type?.name.toString())

        cv_Stats.setCardBackgroundColor(color)
        tv_moves.setTextColor(color)
        tv_evolutions.setTextColor(color)
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
                    Utis.typeColor(detailPokemon.types?.get(0)?.type?.name.toString())
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
                    Utis.typeColor(detailPokemon.types?.get(0)?.type?.name.toString())
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
                    Utis.typeColor(detailPokemon.types?.get(0)?.type?.name.toString())
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