package com.example.pokemon.common

import android.content.Context
import android.net.ConnectivityManager
import com.example.pokemon.R

object Utis {
    val LAUNCH_SECOND_ACTIVITY = 1
    fun amIConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    fun toUpperCase(name: String): String {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase()
    }

    fun cutId(url: String): String {
        var count = 0
        var n = url.length.minus(2)
        var item = url
        for (i in n downTo 0) {
            if (item[i] == '/') {
                count = i
                break
            }
        }
        return item.substring(count + 1, n + 1)
    }
    val POKEMON_TYPE = mapOf<String, Int>(
        "BUG" to R.drawable.ic_bug,
        "DARK" to R.drawable.ic_dark,
        "DRAGON" to R.drawable.ic_dragon,
        "ELECTRIC" to R.drawable.ic_electric,
        "FAIRY" to R.drawable.ic_fairy,
        "FIGHT" to R.drawable.ic_fight,
        "FIGHTING" to R.drawable.ic_fight,
        "FIRE" to R.drawable.ic_fire,
        "FLYING" to R.drawable.ic_flying,
        "GHOST" to R.drawable.ic_ghost,
        "GRASS" to R.drawable.ic_grass,
        "GROUND" to R.drawable.ic_ground,
        "ICE" to R.drawable.ic_ice,
        "NORMAL" to R.drawable.ic_normal,
        "POISON" to R.drawable.ic_poison,
        "PSYCHIC" to R.drawable.ic_psychic,
        "ROCK" to R.drawable.ic_rock,
        "STEEL" to R.drawable.ic_steel,
        "WATER" to R.drawable.ic_water
    )

    fun typeLG(type: String):Int {
        return when (type) {
            "bug" -> R.drawable.tag_bug
            "dark" -> R.drawable.tag_dark
            "dragon" -> R.drawable.tag_dragon
            "electric" -> R.drawable.tag_electric
            "fairy" -> R.drawable.tag_fairy
            "fight" -> R.drawable.tag_fight
            "fighting" -> R.drawable.tag_fight
            "fire" -> R.drawable.tag_fire
            "flying" -> R.drawable.tag_flying
            "ghost" -> R.drawable.tag_ghost
            "grass" -> R.drawable.tag_grass
            "ground" -> R.drawable.tag_ground
            "ice" -> R.drawable.tag_ice
            "normal" -> R.drawable.tag_normal
            "poison" -> R.drawable.tag_poison
            "psychic" -> R.drawable.tag_psychic
            "rock" -> R.drawable.tag_rock
            "steel" -> R.drawable.tag_steel
            "water" -> R.drawable.tag_water
            else -> R.drawable.tag_bug
        }
    }

    fun typeColor(type: String): Int {
        return when (type) {
            "bug" -> R.color.colorBug
            "dark" -> R.color.colorDark
            "dragon" -> R.color.colorDragon
            "electric" -> R.color.colorElectric
            "fairy" -> R.color.colorFairy
            "fight" -> R.color.colorFight
            "fighting" -> R.color.colorFight
            "fire" -> R.color.colorFire
            "flying" -> R.color.colorFlying
            "ghost" -> R.color.colorGhost
            "grass" -> R.color.colorGrass
            "ground" -> R.color.colorGround
            "ice" -> R.color.colorIce
            "normal" -> R.color.colorNormalType
            "poison" -> R.color.colorPoison
            "psychic" -> R.color.colorPsychic
            "rock" -> R.color.colorRock
            "steel" -> R.color.colorSteel
            "water" -> R.color.colorWater
            else -> R.color.colorBug
        }

    }
}