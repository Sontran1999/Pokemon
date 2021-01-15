package com.example.pokemon.views.activity

import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.pokemon.R
import com.example.pokemon.adapter.PokemonAdapter
import com.example.pokemon.common.Utis
import com.example.pokemon.models.detailpokemon.DetailPokemon
import com.example.pokemon.models.pokemons.Pokemon
import com.example.pokemon.viewmodel.ViewModelAPI
import kotlinx.android.synthetic.main.activity_pokemon.*


class PokemonActivity : AppCompatActivity(), View.OnKeyListener, SwipeRefreshLayout.OnRefreshListener {
    lateinit var adapter: PokemonAdapter
    lateinit var layoutManager: LinearLayoutManager
    lateinit var viewModelAPI: ViewModelAPI
    var notLoading = true
    private var list: MutableList<Pokemon> = mutableListOf()
    var mDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)


        viewModelAPI = ViewModelAPI()
        adapter = PokemonAdapter(this,onItemClick)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        edtSearch.setOnKeyListener(this)
        srl.setOnRefreshListener(this)
        setActionBar()
        replaceData()
        loadFeed()
//        initObserve()
        addsScrollListener()

    }

    private fun replaceData() {
        viewModelAPI.pokemons.observe(this) {
            if(it != null ){
                list = it as MutableList<Pokemon>
                adapter.updatePokemonList(it)
            }
        }
    }

    private fun load() {
        viewModelAPI.pokemons.observe(this) {
            if (it != null) {
                mDialog?.dismiss()
            } else {
                mDialog?.dismiss()
                Toast.makeText(this, "error loading from API", Toast.LENGTH_SHORT).show()
            }
        }
        viewModelAPI.getAllPokemon(0)
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
            adapter.reset()
            load()
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

//    private fun initObserve() {
//        viewModelAPI.pokemons.observe(this) {
//            it?.let { pokemon ->
//                list = pokemon as ArrayList<Pokemon>
//                adapter.updatePokemonList(pokemon)
//            }
//        }
//        viewModelAPI.getAllPokemon(0)
//    }

    private fun loadMore() {
        viewModelAPI.pokemons.observe(this) {
            adapter.setList3(list)
            if (it != null) {
                adapter.setList4(it as MutableList<Pokemon>)
                notLoading = true
            }
        }
        viewModelAPI.getAllPokemon(list.size - 1)
    }

    private fun addsScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (notLoading && layoutManager.findLastCompletelyVisibleItemPosition() == list.size - 1) {
                    adapter.setList1(list)
                    notLoading = false
                    loadMore()
                }
            }
        })
    }

    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        var query: String = edtSearch.text.toString()
        when (v?.id) {
            R.id.edtSearch -> {
                if ((event?.action == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        if(query != ""){
                            searchPokemon(edtSearch.text.toString())
                            Toast.makeText(this, edtSearch.text.toString(),Toast.LENGTH_SHORT).show()
                        }else {
                            Toast.makeText(this, "rỗng",Toast.LENGTH_SHORT).show()
                        }
                    return true
                }
                return false
            }
            else -> return false
        }
    }

    private fun searchPokemon(namePokemon: String){
        viewModelAPI.pokemons.observe(this) {
            if (it?.size == 0) {
                AlertDialog.Builder(this).setTitle("No information")
                    .setMessage("The information you are looking for is not available")
                    .setNegativeButton(
                        "OK ",
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            dialogInterface.cancel()
                        }).show()
            }
        }
        viewModelAPI.searchPokemon(namePokemon,list)
    }

    private val onItemClick: (Pokemon) -> Unit = {
        var bundle: Bundle = Bundle()
        bundle.putSerializable("object", it)
        val intent: Intent = Intent(this@PokemonActivity, DetailActivity::class.java)
        intent.putExtra("data", bundle)
//        startActivityForResult(intent, Utis.LAUNCH_SECOND_ACTIVITY)
        startActivity(intent)
    }

    override fun onRefresh() {
        loadFeed()
        Toast.makeText(this, list.size.toString(),Toast.LENGTH_SHORT).show()
        srl.isRefreshing = false
    }


}