package com.example.aplikasievent.ui.Favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplikasievent.Event
import com.example.aplikasievent.databinding.FragmentFavouriteBinding

class FavouriteFragment : Fragment() {

    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!
    private val adapter = FavouriteAdapter { event ->
        navigateToDetail(event)
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

        FavouriteManager.init(requireContext())

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        favouriteViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.recyclerView.visibility = if (isLoading) View.GONE else View.VISIBLE
        }

        favouriteViewModel.favoriteEvents.observe(viewLifecycleOwner) { events ->
            adapter.submitList(events)
        }

        favouriteViewModel.loadFavorites()
    }

    private fun navigateToDetail(event: Event) {
        if (event.isFinished) {
            navigateToDetailFinished(event.id)
        } else {
            navigateToDetailUpcoming(event.id)
        }
    }

    private fun navigateToDetailUpcoming(eventId: Int) {
        val action = FavouriteFragmentDirections
            .actionNavigationFavouriteToDetailFragmentUpcoming(eventId)
        findNavController().navigate(action)
    }

    private fun navigateToDetailFinished(eventId: Int) {
        val action = FavouriteFragmentDirections
            .actionNavigationFavouriteToDetailFragmentFinished(eventId)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
