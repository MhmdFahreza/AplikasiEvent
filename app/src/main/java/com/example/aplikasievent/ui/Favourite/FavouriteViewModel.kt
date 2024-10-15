package com.example.aplikasievent.ui.Favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasievent.Event
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FavouriteViewModel : ViewModel() {
    private val _favoriteEvents = MutableLiveData<List<Event>>(emptyList())
    val favoriteEvents: LiveData<List<Event>> = _favoriteEvents

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // Simulasi request ke API menggunakan coroutine
    fun loadFavorites() {
        viewModelScope.launch {
            _isLoading.value = true
            delay(500)
            _favoriteEvents.value = FavouriteManager.getFavourites()
            _isLoading.value = false
        }
    }

    fun addFavorite(event: Event) {
        FavouriteManager.addFavourite(event)
        _favoriteEvents.value = FavouriteManager.getFavourites()
    }

    fun removeFavorite(event: Event) {
        FavouriteManager.removeFavourite(event)
        _favoriteEvents.value = FavouriteManager.getFavourites()
    }
}
