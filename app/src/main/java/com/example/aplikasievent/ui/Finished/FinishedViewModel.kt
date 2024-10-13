package com.example.aplikasievent.ui.Finished

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasievent.network.RetrofitInstance
import kotlinx.coroutines.launch

class FinishedViewModel : ViewModel() {

    private val _finishedEvents = MutableLiveData<List<ListEventsItem>>()
    val finishedEvents: LiveData<List<ListEventsItem>> = _finishedEvents

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    init {
        getFinishedEvents()
    }

    private fun getFinishedEvents() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getFinishedEvents()
                _finishedEvents.postValue(response.listEvents)
                response.listEvents.forEach {
                    Log.d("FinishedViewModel", "Image URL: ${it.mediaCover}")
                }
            } catch (e: Exception) {
                _errorMessage.postValue(e.message)
            }
        }
    }

}
