package com.example.aplikasievent.ui.Upcoming

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasievent.network.RetrofitInstance
import kotlinx.coroutines.launch

class UpcomingViewModel : ViewModel() {

    private val _upcomingEvents = MutableLiveData<List<ListEventsItem>>()
    val upcomingEvents: LiveData<List<ListEventsItem>> = _upcomingEvents

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    init {
        getUpcomingEvents()
    }

    private fun getUpcomingEvents() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getUpcomingEvents()
                _upcomingEvents.postValue(response.listEvents)
                response.listEvents.forEach {
                    Log.d("UpcomingViewModel", "Image URL: ${it.mediaCover}")
                }
            } catch (e: Exception) {
                _errorMessage.postValue(e.message)
            }
        }
    }

}
