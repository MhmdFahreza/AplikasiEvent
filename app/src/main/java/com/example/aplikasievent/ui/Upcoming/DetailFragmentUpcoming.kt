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
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.aplikasievent.Event
import com.example.aplikasievent.R
import com.example.aplikasievent.databinding.FragmentDetailUpcomingBinding

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

        val eventId = arguments?.getInt("eventId") ?: return

        binding.progressBar.visibility = View.VISIBLE
        binding.linkButton.visibility = View.GONE

        viewModel.upcomingEvents.observe(viewLifecycleOwner, Observer { events ->
            val event = events.find { it.id == eventId }
            if (event != null) {
                bindEventData(event)
                binding.progressBar.visibility = View.GONE
                binding.linkButton.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
                binding.linkButton.visibility = View.GONE
            }
        })
    }

    private fun bindEventData(event: Event) {
        binding.name.text = event.name
        binding.ownerName.text = event.ownerName
        binding.beginTime.text = event.beginTime

        val formattedDescription = HtmlCompat.fromHtml(event.description, HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.description.text = formattedDescription

        val remainingQuota = event.quota - event.registrants
        binding.sisaKuota.text = "Sisa Kuota: $remainingQuota"

        val placeholderRes = when (event.id % 3) {
            1 -> R.drawable.bootcamp
            2 -> R.drawable.devkoch173
            else -> R.drawable.error_image
        }

        Glide.with(this)
            .load(event.imageUrl)
            .placeholder(placeholderRes)
            .into(binding.mediaCover)

        binding.linkButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(event.link)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
