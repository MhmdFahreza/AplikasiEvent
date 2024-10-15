package com.example.aplikasievent

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.aplikasievent.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    private val PREFS_NAME = "theme_prefs"
    private val PREFS_THEME_KEY = "current_theme"

    override fun onCreate(savedInstanceState: Bundle?) {
        // Load theme preference before calling super.onCreate()
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isDarkTheme = sharedPreferences.getBoolean(PREFS_THEME_KEY, false)
        setAppTheme(isDarkTheme)

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_upcoming, R.id.navigation_finished, R.id.navigation_favourite, R.id.navigation_setting)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun setAppTheme(isDarkTheme: Boolean) {
        if (isDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}
