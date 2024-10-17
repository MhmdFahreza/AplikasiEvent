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
        return when (position % 39 + 1) {
            1 -> R.drawable.idcamp_x_dicoding_live_2_automation_fast_track_your_career
            2 -> R.drawable.devkoch173
            3 -> R.drawable.dosdevcoach_172
            4 -> R.drawable.offline_event_baparekraf
            5 -> R.drawable.devcoach_171_machine_learning_in_google
            6 -> R.drawable.devcoach_170_data_science
            7 -> R.drawable.idcamp_x_dicoding_live_1_beyond_the_basics_elevate_your_career_as_a_full_stack
            8 -> R.drawable.devcoach_169
            9 -> R.drawable.devcoach_168
            10 -> R.drawable.devcoach_167
            11 -> R.drawable.devcoach_166
            12 -> R.drawable.devcoach_165
            13 -> R.drawable.dicoding_ignite_path
            14 -> R.drawable.devcoach_164
            15 -> R.drawable.devcoach_163
            16 -> R.drawable.devcoach_162
            17 -> R.drawable.idcamp_x_dicoding_live_deep_learning
            18 -> R.drawable.tech_meetup_berkarya
            19 -> R.drawable.study_jam_laravel_authentication
            20 -> R.drawable.dicoding_bootcamp_trial_session_4
            21 -> R.drawable.devcoach_161
            22 -> R.drawable.study_jam_laravel_laravel_rest_api_week_3
            23 -> R.drawable.devcoach_160
            24 -> R.drawable.study_jam_laravel_introduction_to_laravel_week_2
            25 -> R.drawable.devcoach_159
            26 -> R.drawable.study_jam_laravel_introduction_to_laravel_week_1
            27 -> R.drawable.devcoach_158
            28 -> R.drawable.dicoding_sharing_session_unlocking_creativity
            29 -> R.drawable.building_performant_web_applications
            30 -> R.drawable.devcoach_157
            31 -> R.drawable.devcoach_156
            32 -> R.drawable.integrate_gen_ai
            33 -> R.drawable.devcoach_155
            34 -> R.drawable.the_blueprint_for_android_app_success
            35 -> R.drawable.webcode_complete
            36 -> R.drawable.devcoach_154
            37 -> R.drawable.wikidroid_ui_slicing
            38 -> R.drawable.maximize_your_content_with_beautiful_assets
            39 -> R.drawable.pkm_instiki_techfest_2024_
            else -> R.drawable.error_image
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
