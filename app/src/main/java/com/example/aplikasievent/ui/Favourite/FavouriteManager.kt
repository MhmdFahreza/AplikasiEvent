package com.example.aplikasievent.ui.Favourite

import android.content.Context
import android.content.SharedPreferences
import com.example.aplikasievent.Event
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object FavouriteManager {
    private const val PREF_NAME = "favourite_prefs"
    private const val FAVOURITE_KEY = "favourite_list"

    private val favourites = mutableListOf<Event>()
    private lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        loadFavourites()
    }

    fun addFavourite(event: Event) {
        if (!favourites.any { it.id == event.id }) {
            favourites.add(event)
            saveFavourites()
        }
    }

    fun removeFavourite(event: Event) {
        favourites.remove(event)
        saveFavourites()
    }

    fun getFavourites(): List<Event> {
        return favourites
    }

    fun isFavourite(event: Event): Boolean {
        return favourites.any { it.id == event.id }
    }

    private fun saveFavourites() {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(favourites)
        editor.putString(FAVOURITE_KEY, json)
        editor.apply()
    }

    private fun loadFavourites() {
        val json = sharedPreferences.getString(FAVOURITE_KEY, null)
        if (!json.isNullOrEmpty()) {
            val type = object : TypeToken<List<Event>>() {}.type
            val loadedFavourites: List<Event> = gson.fromJson(json, type)
            favourites.clear()
            favourites.addAll(loadedFavourites)
        }
    }
}
