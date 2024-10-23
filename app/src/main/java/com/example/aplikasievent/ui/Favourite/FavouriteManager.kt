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

    // Initialize SharedPreferences
    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        loadFavourites() // Load favourites from storage when app starts
    }

    // Add event to favourites
    fun addFavourite(event: Event) {
        if (!favourites.any { it.id == event.id }) { // Only add if it doesn't already exist
            favourites.add(event)
            saveFavourites() // Save changes to SharedPreferences
        }
    }

    // Remove event from favourites
    fun removeFavourite(event: Event) {
        favourites.remove(event)
        saveFavourites() // Save changes after removal
    }

    // Get the list of favourite events
    fun getFavourites(): List<Event> {
        return favourites
    }

    // Check if an event is in the favourites list
    fun isFavourite(event: Event): Boolean {
        return favourites.any { it.id == event.id }
    }

    // Save the current list of favourites to SharedPreferences
    private fun saveFavourites() {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(favourites) // Convert list to JSON
        editor.putString(FAVOURITE_KEY, json)
        editor.apply() // Save JSON to SharedPreferences
    }

    // Load the list of favourites from SharedPreferences
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
