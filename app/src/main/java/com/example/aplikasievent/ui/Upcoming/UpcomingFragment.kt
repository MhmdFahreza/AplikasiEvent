package com.example.aplikasievent.ui.Upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.aplikasievent.databinding.FragmentUpcomingBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UpcomingFragment : Fragment() {

    private var _binding: FragmentUpcomingBinding? = null
    private val binding get() = _binding!!

    private lateinit var upcomingViewModel: UpcomingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Initialize ViewModel
        upcomingViewModel = ViewModelProvider(this).get(UpcomingViewModel::class.java)

        _binding = FragmentUpcomingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.eventNameUpcoming
        val progressBar: ProgressBar = binding.progressBarUpcoming

        // Show loading indicator while data is being fetched
        lifecycleScope.launch {
            progressBar.visibility = View.VISIBLE
            delay(2000) // Simulate loading
            progressBar.visibility = View.GONE
        }

        // Observe the upcomingEvents LiveData
        upcomingViewModel.upcomingEvents.observe(viewLifecycleOwner) { events ->
            if (events.isNotEmpty()) {
                textView.text = events[0].name
            } else {
                textView.text = "No upcoming events available"
            }
        }

        // Observe the errorMessage LiveData
        upcomingViewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            // Display the error message if there's an error
            textView.text = errorMessage
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
