package com.example.pokemon.common

import android.content.Context
import android.net.ConnectivityManager

object Utis {
    val LAUNCH_SECOND_ACTIVITY = 1
    fun amIConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    fun toUpperCase( name: String): String{
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase()
    }
}