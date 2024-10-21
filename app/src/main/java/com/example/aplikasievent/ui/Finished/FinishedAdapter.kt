package com.example.aplikasievent.ui.Finished

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplikasievent.Event
import com.example.aplikasievent.R
import com.example.aplikasievent.databinding.ItemEventFinishedBinding


class FinishedAdapter : RecyclerView.Adapter<FinishedAdapter.EventViewHolder>() {

    private var eventList: List<Event> = listOf()

    class EventViewHolder(private val binding: ItemEventFinishedBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event, position: Int) {
            binding.eventName.text = event.name


            val placeholderRes = getPlaceholderImage(position)

            Glide.with(binding.eventImage.context)
                .load(event.imageUrl)
                .placeholder(placeholderRes)
                .into(binding.eventImage)


            binding.root.setOnClickListener {
                val action = FinishedFragmentDirections.actionNavigationFinishedToDetailFragmentFinished(event.id)
                it.findNavController().navigate(action)
            }
        }

        private fun getPlaceholderImage(position: Int): Int {
            return when (position % 37 + 1) {
                1 -> R.drawable.bootcamp
                2 -> R.drawable.idcamp_x_dicoding_live_2_automation_fast_track_your_career
                3 -> R.drawable.devkoch173
                4 -> R.drawable.dosdevcoach_172
                5 -> R.drawable.offline_event_baparekraf
                6 -> R.drawable.devcoach_171_machine_learning_in_google
                7 -> R.drawable.devcoach_170_data_science
                8 -> R.drawable.idcamp_x_dicoding_live_1_beyond_the_basics_elevate_your_career_as_a_full_stack
                9 -> R.drawable.devcoach_169
                10 -> R.drawable.devcoach_168
                11 -> R.drawable.devcoach_167
                12 -> R.drawable.devcoach_166
                13 -> R.drawable.devcoach_165
                14 -> R.drawable.dicoding_ignite_path
                15 -> R.drawable.devcoach_164
                16 -> R.drawable.devcoach_163
                17 -> R.drawable.devcoach_162
                18 -> R.drawable.idcamp_x_dicoding_live_deep_learning
                19 -> R.drawable.tech_meetup_berkarya
                20 -> R.drawable.study_jam_laravel_authentication
                21 -> R.drawable.dicoding_bootcamp_trial_session_4
                22 -> R.drawable.devcoach_161
                23 -> R.drawable.study_jam_laravel_laravel_rest_api_week_3
                24 -> R.drawable.devcoach_160
                25 -> R.drawable.study_jam_laravel_introduction_to_laravel_week_2
                26 -> R.drawable.devcoach_159
                27 -> R.drawable.study_jam_laravel_introduction_to_laravel_week_1
                28 -> R.drawable.devcoach_158
                29 -> R.drawable.dicoding_sharing_session_unlocking_creativity
                30 -> R.drawable.building_performant_web_applications
                31 -> R.drawable.devcoach_157
                32 -> R.drawable.devcoach_156
                33 -> R.drawable.integrate_gen_ai
                34 -> R.drawable.devcoach_155
                35 -> R.drawable.the_blueprint_for_android_app_success
                36 -> R.drawable.webcode_complete
                37 -> R.drawable.devcoach_154
                else -> R.drawable.error_image
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventFinishedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(eventList[position], position)
    }

    override fun getItemCount(): Int = eventList.size

    fun submitList(events: List<Event>) {
        this.eventList = events
        notifyDataSetChanged()
    }
}
