package com.example.aplikasievent.ui.Setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingViewModel : ViewModel() {

    private val _isDarkMode = MutableLiveData<Boolean>()
    val isDarkMode: LiveData<Boolean> get() = _isDarkMode

    fun setDarkModeEnabled(enabled: Boolean) {
        _isDarkMode.value = enabled
    }

    fun toggleTheme() {
        _isDarkMode.value = _isDarkMode.value != true
    }


}
