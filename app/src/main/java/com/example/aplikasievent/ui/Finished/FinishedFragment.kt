package com.example.aplikasievent.ui.Finished

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplikasievent.R
import com.example.aplikasievent.databinding.FragmentFinishedBinding

class FinishedFragment : Fragment() {

    private var _binding: FragmentFinishedBinding? = null
    private val binding get() = _binding!!

    private lateinit var finishedAdapter: FinishedAdapter
    private lateinit var finishedViewModel: FinishedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        finishedViewModel = ViewModelProvider(this).get(FinishedViewModel::class.java)

        _binding = FragmentFinishedBinding.inflate(inflater, container, false)

        // Initialize adapter with click listener
        finishedAdapter = FinishedAdapter { event ->
            // Navigate to DetailFragmentFinished when an event is clicked
            findNavController().navigate(R.id.action_navigation_finished_to_detailFragmentFinished)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = finishedAdapter
        }

        finishedViewModel.finishedEvents.observe(viewLifecycleOwner) { events ->
            finishedAdapter.submitList(events)
        }

        finishedViewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
