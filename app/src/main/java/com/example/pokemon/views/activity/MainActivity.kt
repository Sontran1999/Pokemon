package com.example.pokemon.views.activity

import android.app.ActivityOptions.makeCustomAnimation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.pokemon.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBar()
//        val backgroundImage: ImageView = findViewById(R.id.splash)
//        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha_in)
//        backgroundImage.startAnimation(slideAnimation)
        var handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                finish()
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }

        }, 3000)

    }

    override fun finish() {
        super.finish()
        startActivity(Intent(this, PokemonActivity::class.java))
    }

    fun setActionBar() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        )
    }

}
