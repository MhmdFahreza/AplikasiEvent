package com.example.aplikasievent.ui.Finished

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.aplikasievent.databinding.FragmentDetailFinishedBinding

class DetailFragmentFinished : Fragment() {

    private var _binding: FragmentDetailFinishedBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FinishedViewModel by viewModels()  // ViewModel injection

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mengambil ID event dari argument
        val eventId = arguments?.getInt("eventId") ?: return

        // Observe data dari ViewModel dan tampilkan di UI
        viewModel.getEventById(eventId).observe(viewLifecycleOwner) { event ->
            event?.let {
                // Mengisi data ke dalam UI
                binding.name.text = event.name
                binding.ownerName.text = event.ownerName
                binding.beginTime.text = event.beginTime
                binding.quota.text = "${event.quota - event.registrant} kuota tersisa"
                binding.description.text = event.description

                // Load gambar menggunakan Glide
                Glide.with(this)
                    .load(event.imageUrl)
                    .into(binding.mediaCover)

                // Set tombol untuk membuka link acara
                binding.linkButton.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(event.link)
                    startActivity(intent)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
