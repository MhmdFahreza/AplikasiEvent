package com.example.aplikasievent.ui.Upcoming

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.aplikasievent.Event
import com.example.aplikasievent.R
import com.example.aplikasievent.databinding.FragmentDetailUpcomingBinding
import com.example.aplikasievent.ui.Favourite.FavouriteManager

class DetailFragmentUpcoming : Fragment() {

    private var _binding: FragmentDetailUpcomingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UpcomingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailUpcomingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FavouriteManager.init(requireContext())

        val eventId = arguments?.getInt("eventId") ?: return

        binding.progressBar.visibility = View.VISIBLE
        binding.linkButton.visibility = View.GONE

        viewModel.upcomingEvents.observe(viewLifecycleOwner) { events ->
            val event = events.find { it.id == eventId }
            if (event != null) {
                val position = events.indexOf(event)
                bindEventData(event, position)
                binding.progressBar.visibility = View.GONE
                binding.linkButton.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
                binding.linkButton.visibility = View.GONE
            }
        }
    }

    private fun bindEventData(event: Event, position: Int) {
        binding.name.text = event.name
        binding.ownerName.text = event.ownerName
        binding.beginTime.text = event.beginTime

        val formattedDescription = HtmlCompat.fromHtml(event.description, HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.description.text = formattedDescription

        val remainingQuota = event.quota - event.registrants
        binding.sisaKuota.text = "Sisa Kuota: $remainingQuota"

        val placeholderRes = getPlaceholderImage(position)
        Glide.with(this)
            .load(event.imageUrl)
            .placeholder(placeholderRes)
            .into(binding.mediaCover)

        binding.linkButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(event.link)
            startActivity(intent)
        }

        val isFavourited = FavouriteManager.isFavourite(event)
        updateFavouriteButtonUI(isFavourited)

        binding.favouriteButton.setOnClickListener {
            if (isFavourited) {
                FavouriteManager.removeFavourite(event)
                updateFavouriteButtonUI(false)
            } else {
                FavouriteManager.addFavourite(event)
                updateFavouriteButtonUI(true)
            }
        }
    }

    private fun updateFavouriteButtonUI(isFavourited: Boolean) {
        if (isFavourited) {
            binding.favouriteButton.setImageResource(R.drawable.deletefavourite)
            binding.favouriteButton.setColorFilter(resources.getColor(android.R.color.black, null))
        } else {
            binding.favouriteButton.setImageResource(R.drawable.addfavourite)
            binding.favouriteButton.setColorFilter(resources.getColor(android.R.color.white, null))
        }
    }

    private fun getPlaceholderImage(position: Int): Int {
        return when (position % 38 + 1) {
            1 -> R.drawable.cara_mencari_dan_melamar_pekerjaan_di_upwork
            2 -> R.drawable.devcoach_174
            else -> R.drawable.error_image
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
