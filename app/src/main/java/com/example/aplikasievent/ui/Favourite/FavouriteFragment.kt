package com.example.aplikasievent.ui.Favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplikasievent.databinding.FragmentFavouriteBinding

class FavouriteFragment : Fragment() {

    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!
    private val adapter = FavouriteAdapter { event ->
        navigateToDetail(event.id, event.isFavorite)
    }
    private val favouriteViewModel: FavouriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize FavouriteManager with context
        FavouriteManager.init(requireContext())

        // Setup RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        // Observe loading state
        favouriteViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
            }
        }

        // Observe favourite events data
        favouriteViewModel.favoriteEvents.observe(viewLifecycleOwner) { events ->
            adapter.submitList(events)
        }

        // Load data
        favouriteViewModel.loadFavorites()
    }

    private fun navigateToDetail(eventId: Int, isFinished: Boolean) {
        if (isFinished) {
            val action = FavouriteFragmentDirections.actionNavigationFavouriteToDetailFragmentFinished(eventId)
            findNavController().navigate(action)
        } else {
            val action = FavouriteFragmentDirections.actionNavigationFavouriteToDetailFragmentUpcoming(eventId)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
