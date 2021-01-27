package com.example.pokemon.views.activity

import android.app.ActivityOptions
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.pokemon.R
import com.example.pokemon.adapter.PokemonAdapter
import com.example.pokemon.common.Utis
import com.example.pokemon.models.detailpokemon.DetailPokemon
import com.example.pokemon.models.pokemons.Pokemon
import com.example.pokemon.models.pokemons.PokemonResponse
import com.example.pokemon.viewmodel.ViewModelAPI
import kotlinx.android.synthetic.main.activity_pokemon.*


class PokemonActivity : AppCompatActivity(), View.OnKeyListener,
    SwipeRefreshLayout.OnRefreshListener {
    lateinit var adapter: PokemonAdapter
    lateinit var layoutManager: LinearLayoutManager
    lateinit var viewModelAPI: ViewModelAPI
    var keySearch = false
    var keyDisplay = false
    var check = false
    private lateinit var listPokemon: PokemonResponse
    var list: MutableList<DetailPokemon> = arrayListOf()
    private var mDialog: ProgressDialog? = null
    var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)

        viewModelAPI = ViewModelAPI()
        adapter = PokemonAdapter(this, onItemClick)
        listPokemon = PokemonResponse()
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

//        edtSearch.setOnKeyListener(this)
        srl.setOnRefreshListener(this)
        setActionBar()
        registerObserve()
        loadFeed()
        addsScrollListener()

        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (!check) {
                    check = true
                    var handler = Handler()
                    handler.postDelayed(object : Runnable {
                        override fun run() {
                            keySearch = true
                            viewModelAPI.searchPokemon(edtSearch.text.toString(), list)
                            check = false
                        }

                    }, 1000)
                }
            }

        })

    }

    private fun registerObserve() {
        replaceData()
        registerSearchPokemon()
    }

    private fun replaceData() {
        viewModelAPI.pokemons.observe(this) {
            if (it != null) {
                listPokemon = PokemonResponse(it)
                count = 0
                viewModelAPI.setIdPokemon(count.toString())
            } else {
                mDialog?.dismiss()
                Toast.makeText(this, "error loading from API", Toast.LENGTH_SHORT).show()
            }
        }

        viewModelAPI.idPokemon.observe(this) {
            if (it != null) {
                if (list.size == 0) {
                    count = 0
                }
                if (count < listPokemon.results?.size ?: 0) {
                    var id = listPokemon.results?.get(count)?.name
                    if (id != null) {
                        viewModelAPI.getDetailPokemon(id)
                    }
                } else {
                    mDialog?.dismiss()
                    keyDisplay = true
                    adapter.updatePokemonList(list)
                }

            }
        }

        viewModelAPI.detailPokemon.observe(this) {
            if (keySearch) {
                if (it == null) {
                    AlertDialog.Builder(this).setTitle("No information")
                        .setMessage("The information you are looking for is not available")
                        .setNegativeButton(
                            "OK ",
                            DialogInterface.OnClickListener { dialogInterface, i ->
                                dialogInterface.cancel()
                            }).show()
                    edtSearch.text.clear()
                } else {
                    list.clear()
                    list.add(it)
                    adapter.updatePokemonList(list)
                    keySearch = false
                    keyDisplay = false
                    edtSearch.text.clear()
                }
            } else if (it != null && !keySearch) {
                    list.add(it)
                    count++
                    viewModelAPI.setIdPokemon(count.toString())
            }
        }

    }


    private fun loadFeed() {
        mDialog = ProgressDialog(this)
        mDialog?.setMessage("Loading Data...")
        mDialog?.setCancelable(false)
//        mDialog?.setIndeterminate(true);
        mDialog?.setButton(
            "Cancel", DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.cancel()
            })
        mDialog?.show()
        if (Utis.amIConnected(this)) {
            edtSearch.text.clear()
            viewModelAPI.getAllPokemon(0)
        } else {
            Toast.makeText(this, "Thiết bị chưa kết nối internet", Toast.LENGTH_SHORT).show()
            mDialog?.dismiss()
        }
    }

    private fun setActionBar() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    private fun addsScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!adapter.isLoading && !recyclerView.canScrollVertically(1) && keyDisplay) {
                    listPokemon = PokemonResponse()
                    viewModelAPI.getAllPokemon(list.size)
                    adapter.setLoadMoreItem(true)
                    Log.d("loadmore", "poke")
                }
            }
        })
    }

    private fun registerSearchPokemon() {
        viewModelAPI.searchPokemon.observe(this) {
            if (it.size != 0) {
                adapter.updatePokemonList(it)
//                edtSearch.text.clear()
                keySearch = false
                keyDisplay = false
            } else {
                AlertDialog.Builder(this).setTitle("No information")
                    .setMessage("The information you are looking for is not available")
                    .setNegativeButton(
                        "OK ",
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            dialogInterface.cancel()
                        }).show()
//                edtSearch.text.clear()
            }
        }
    }


    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        var query: String = edtSearch.text.toString()
        when (v?.id) {
            R.id.edtSearch -> {
                if ((event?.action == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)
                ) {
                    if (query != "") {
                        keySearch = true
//                        viewModelAPI.getDetailPokemon(edtSearch.text.toString())
                        viewModelAPI.searchPokemon(edtSearch.text.toString(), list)
                    } else {
                        Toast.makeText(
                            this,
                            "Please type name or id of Pokemon!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                    return true
                }
                return false
            }
            else -> return false
        }
    }

    private val onItemClick: (DetailPokemon) -> Unit = {
        var bundle: Bundle = Bundle()
        bundle.putSerializable("object", it)
        val intent: Intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("data", bundle)
        startActivity(intent)

    }

    override fun onRefresh() {
        list.clear()
        listPokemon = PokemonResponse()
        viewModelAPI.call?.cancel()
        loadFeed()
        srl.isRefreshing = false
    }


}