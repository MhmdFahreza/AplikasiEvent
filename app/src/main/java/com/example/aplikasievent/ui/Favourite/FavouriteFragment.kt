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
        when (event.id) {
            8938, 8963 -> navigateToDetailUpcoming(event.id)
            8958, 8948, 8943, 8953, 8933 -> navigateToDetailFinished(event.id)
            8898, 8928, 8923, 8918, 8908 -> navigateToDetailFinished(event.id)
            8903, 8893, 8883, 8878, 8868 -> navigateToDetailFinished(event.id)
            8863, 8853, 8848, 8808, 8833 -> navigateToDetailFinished(event.id)
            8838, 8828, 8823, 8818, 8813 -> navigateToDetailFinished(event.id)
            8798, 8793, 8778, 8783, 8788 -> navigateToDetailFinished(event.id)
            8747, 8772, 8752, 8742, 8727 -> navigateToDetailFinished(event.id)
            8682, 8712, 8722 -> navigateToDetailFinished(event.id)
            else -> println("Unknown event")
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


