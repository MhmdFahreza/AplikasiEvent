package com.example.aplikasievent.ui.Finished

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import com.example.aplikasievent.databinding.FragmentDetailFinishedBinding

class DetailFragmentFinished : Fragment() {

    private var _binding: FragmentDetailFinishedBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FinishedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val eventId = arguments?.getInt("eventId") ?: return

        // Sembunyikan link button saat loading
        binding.linkButton.visibility = View.GONE
        // Tampilkan indikator loading saat data dimuat
        binding.progressBar.visibility = View.VISIBLE

        // Delay 2 detik untuk simulasi loading
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.finishedEvents.observe(viewLifecycleOwner, Observer { events ->
                val event = events.find { it.id == eventId }
                event?.let {
                    val position = events.indexOf(it)
                    bindEventData(it, position)
                }
                // Hilangkan indikator loading setelah data dimuat
                binding.progressBar.visibility = View.GONE
                // Tampilkan link button setelah loading selesai
                binding.linkButton.visibility = View.VISIBLE
            })
        }, 2000)
    }

    private fun bindEventData(event: Event, position: Int) {
        binding.name.text = event.name
        binding.ownerName.text = event.ownerName
        binding.beginTime.text = event.beginTime

        val remainingQuota = event.quota - event.registrant
        binding.quota.text = "$remainingQuota kuota tersisa"

        val formattedDescription = HtmlCompat.fromHtml(event.description, HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.description.text = formattedDescription

        val placeholderRes = getPlaceholderImage(position)

        // Load image with the selected placeholder
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

    private fun getPlaceholderImage(position: Int): Int {
        return when (position % 38 + 1) {
            1 -> R.drawable.dosdevcoach_172
            2 -> R.drawable.offline_event_baparekraf
            // Add other cases here...
            else -> R.drawable.error_image
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
