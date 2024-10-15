package com.example.aplikasievent.ui.Upcoming

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasievent.Event
import com.example.aplikasievent.network.RetrofitInstance
import kotlinx.coroutines.launch

class UpcomingViewModel : ViewModel() {

    private val _upcomingEvents = MutableLiveData<List<Event>>()
    val upcomingEvents: LiveData<List<Event>> = _upcomingEvents

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    init {
        getUpcomingEvents()
    }

    private fun getUpcomingEvents() {
        viewModelScope.launch {
            try {
                _loading.postValue(true)
                val response = RetrofitInstance.api.getUpcomingEvents()
                _upcomingEvents.postValue(response.listEvents)
            } catch (e: Exception) {
                e.printStackTrace()
                _upcomingEvents.postValue(emptyList())
            } finally {
                _loading.postValue(false)
            }
        }
    }
}
