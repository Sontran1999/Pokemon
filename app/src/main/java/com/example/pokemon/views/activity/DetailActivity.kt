package com.example.pokemon.views.activity

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.res.ColorStateList
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.observe
import com.example.pokemon.R
import com.example.pokemon.common.Utis
import com.example.pokemon.models.detailpokemon.DetailPokemon
import com.example.pokemon.models.pokemons.Pokemon
import com.example.pokemon.viewmodel.ViewModelAPI
import com.example.pokemon.views.fragment.EvolutionsFragment
import com.example.pokemontest.MovesFragment
import com.example.pokemontest.StatsFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_stats.*
import java.io.Serializable

class DetailActivity : AppCompatActivity(), View.OnClickListener, Serializable {
    private val listFragment = arrayListOf(StatsFragment(), EvolutionsFragment(), MovesFragment())
    private val fragmentManager: FragmentManager = supportFragmentManager
    private val fragmentTransaction = fragmentManager.beginTransaction()
    private var count = 0
    private var pokemon: Pokemon? = null
    private var detailPokemon: DetailPokemon? = null
    lateinit var viewModelAPI: ViewModelAPI
    var mDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var bundle = intent.getBundleExtra("data")
        pokemon = bundle?.getSerializable("object") as Pokemon
        viewModelAPI = ViewModelAPI()

        getDetail()
        setActionBar()

        listFragment.forEachIndexed { _, fragment ->
            fragmentTransaction.add(R.id.fm_content, fragment)
            fragmentTransaction.hide(fragment)
        }
        fragmentTransaction.show(listFragment[0])
        fragmentTransaction.commit()
    }

    fun setActionBar(){
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        )
    }

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
        tv_HP.text = detailPokemon?.stats?.get(0)?.base_stat.toString()
        tv_ATK.text = detailPokemon?.stats?.get(0)?.base_stat.toString()
        tv_DEF.text = detailPokemon?.stats?.get(0)?.base_stat.toString()
        tv_SATK.text = detailPokemon?.stats?.get(0)?.base_stat.toString()
        tv_SDEF.text = detailPokemon?.stats?.get(0)?.base_stat.toString()
        tv_SPD.text = detailPokemon?.stats?.get(0)?.base_stat.toString()

    }

    private fun getDetail() {
        viewModelAPI.detailPokemon.observe(this) {
            if (it != null) {
                detailPokemon = it
                init()
            }
        }
        viewModelAPI.getDetailPokemon(pokemon?.id.toString())
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
                            R.color.dark_sky_blue
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
                            R.color.blue
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
                            R.color.blue
                        )
                    )
                )
            }
            R.id.cv_Evolutions -> {
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
                            R.color.dark_sky_blue
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
                            R.color.blue
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
                            R.color.blue
                        )
                    )
                )
            }
            R.id.cv_Moves -> {
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
                            R.color.dark_sky_blue
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
                            R.color.blue
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
                            R.color.blue
                        )
                    )
                )
            }
        }
    }
}