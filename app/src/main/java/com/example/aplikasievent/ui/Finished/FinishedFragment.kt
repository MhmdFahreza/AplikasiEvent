package com.example.aplikasievent.ui.Finished

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.aplikasievent.databinding.FragmentFinishedBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FinishedFragment : Fragment() {

    private var _binding: FragmentFinishedBinding? = null
    private val binding get() = _binding!!

    private lateinit var finishedViewModel: FinishedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Initialize ViewModel
        finishedViewModel = ViewModelProvider(this).get(FinishedViewModel::class.java)

        _binding = FragmentFinishedBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.eventNameFinished
        val progressBar: ProgressBar = binding.progressBarFinished

        // Show loading indicator while data is being fetched
        lifecycleScope.launch {
            progressBar.visibility = View.VISIBLE
            delay(2000) // Simulate loading
            progressBar.visibility = View.GONE
        }

        // Observe the finishedEvents LiveData
        finishedViewModel.finishedEvents.observe(viewLifecycleOwner) { events ->
            if (events.isNotEmpty()) {
                textView.text = events[0].name
            } else {
                textView.text = "No finished events available"
            }
        }

        // Observe the errorMessage LiveData
        finishedViewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
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
