package com.example.aplikasievent.ui.Finished

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
import com.example.aplikasievent.databinding.FragmentDetailFinishedBinding
import com.example.aplikasievent.ui.Favourite.FavouriteManager

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

        // Initialize FavouriteManager with context
        FavouriteManager.init(requireContext())

        val eventId = arguments?.getInt("eventId") ?: return

        binding.progressBar.visibility = View.VISIBLE
        binding.linkButton.visibility = View.GONE

        viewModel.finishedEvents.observe(viewLifecycleOwner, Observer { events ->
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
        })
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

        // Handle favourite
        val isFavourited = FavouriteManager.isFavourite(event)
        binding.favouriteButton.setImageResource(if (isFavourited) R.drawable.ic_favourite_button else R.drawable.ic_favourite_button)

        binding.favouriteButton.setOnClickListener {
            if (isFavourited) {
                FavouriteManager.removeFavourite(event)
                binding.favouriteButton.setImageResource(R.drawable.ic_favourite_button)
            } else {
                FavouriteManager.addFavourite(event)
                binding.favouriteButton.setImageResource(R.drawable.ic_favourite_button)
            }
        }
    }

    private fun getPlaceholderImage(position: Int): Int {
        return when (position % 38 + 1) {
            1 -> R.drawable.dosdevcoach_172
            2 -> R.drawable.offline_event_baparekraf
            3 -> R.drawable.devcoach_171_machine_learning_in_google
            4 -> R.drawable.devcoach_170_data_science
            5 -> R.drawable.idcamp_x_dicoding_live_1_beyond_the_basics_elevate_your_career_as_a_full_stack
            6 -> R.drawable.devcoach_169
            7 -> R.drawable.devcoach_168
            8 -> R.drawable.devcoach_167
            9 -> R.drawable.devcoach_166
            10 -> R.drawable.devcoach_165
            11 -> R.drawable.dicoding_ignite_path
            12 -> R.drawable.devcoach_164
            13 -> R.drawable.devcoach_163
            14 -> R.drawable.devcoach_162
            15 -> R.drawable.idcamp_x_dicoding_live_deep_learning
            16 -> R.drawable.tech_meetup_berkarya
            17 -> R.drawable.study_jam_laravel_authentication
            18 -> R.drawable.dicoding_bootcamp_trial_session_4
            19 -> R.drawable.devcoach_161
            20 -> R.drawable.study_jam_laravel_laravel_rest_api_week_3
            21 -> R.drawable.devcoach_160
            22 -> R.drawable.study_jam_laravel_introduction_to_laravel_week_2
            23 -> R.drawable.devcoach_159
            24 -> R.drawable.study_jam_laravel_introduction_to_laravel_week_1
            25 -> R.drawable.devcoach_158
            26 -> R.drawable.dicoding_sharing_session_unlocking_creativity
            27 -> R.drawable.building_performant_web_applications
            28 -> R.drawable.devcoach_157
            29 -> R.drawable.devcoach_156
            30 -> R.drawable.integrate_gen_ai
            31 -> R.drawable.devcoach_155
            32 -> R.drawable.the_blueprint_for_android_app_success
            33 -> R.drawable.webcode_complete
            34 -> R.drawable.devcoach_154
            35 -> R.drawable.wikidroid_ui_slicing
            36 -> R.drawable.maximize_your_content_with_beautiful_assets
            37 -> R.drawable.pkm_instiki_techfest_2024_
            38 -> R.drawable.devcoach_153
            else -> R.drawable.error_image
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
