package com.example.aplikasievent.ui.Finished

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasievent.Event
import com.example.aplikasievent.network.RetrofitInstance
import kotlinx.coroutines.launch

class FinishedViewModel : ViewModel() {

    private val _finishedEvents = MutableLiveData<List<Event>>()
    val finishedEvents: LiveData<List<Event>> = _finishedEvents

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    init {
        getFinishedEvents()
    }

    private fun getFinishedEvents() {
        viewModelScope.launch {
            try {
                _loading.postValue(true)
                val response = RetrofitInstance.api.getFinishedEvents()
                _finishedEvents.postValue(response.listEvents)
            } catch (e: Exception) {
                e.printStackTrace()
                _finishedEvents.postValue(emptyList())
            } finally {
                _loading.postValue(false)
            }
        }
    }
}
