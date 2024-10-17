package com.example.aplikasievent.ui.Setting

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.aplikasievent.databinding.FragmentSettingBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPreferences: SharedPreferences
    private val PREFS_NAME = "theme_prefs"
    private val PREFS_THEME_KEY = "current_theme"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // No need to apply the theme here, we'll do it in Activity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        // Load saved theme state
        val isDarkTheme = sharedPreferences.getBoolean(PREFS_THEME_KEY, false)
        binding.switchTheme.isChecked = isDarkTheme

        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            // Save theme preference
            sharedPreferences.edit().putBoolean(PREFS_THEME_KEY, isChecked).apply()
            setAppTheme(isChecked)
        }

        // Handle progress bar and background task
        binding.progressBar.visibility = View.VISIBLE
        lifecycleScope.launch(Dispatchers.IO) {
            // Simulate API request
            val result = performApiRequest()
            launch(Dispatchers.Main) {
                binding.progressBar.visibility = View.GONE
                binding.textView.text = result
            }
        }
    }

    private fun setAppTheme(isDarkTheme: Boolean) {
        if (isDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private suspend fun performApiRequest(): String {
        // Simulate a delay to mimic an API call
        kotlinx.coroutines.delay(2000)  // Simulate 2 seconds delay
        return "Pengaturan Tema"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
