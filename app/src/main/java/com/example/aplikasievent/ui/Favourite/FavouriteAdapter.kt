package com.example.aplikasievent.ui.Favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplikasievent.Event
import com.example.aplikasievent.R
import com.example.aplikasievent.databinding.ItemEventFavouriteBinding

class FavouriteAdapter(private val onItemClick: (Event) -> Unit) :
    RecyclerView.Adapter<FavouriteAdapter.EventViewHolder>() {

    private var eventList: List<Event> = listOf()

    class EventViewHolder(private val binding: ItemEventFavouriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event, onItemClick: (Event) -> Unit) {
            binding.eventName.text = event.name

            // Placeholder berdasarkan ID event
            val placeholderRes = getPlaceholderImage(event.id)

            Glide.with(binding.eventImage.context)
                .load(event.imageUrl)
                .placeholder(placeholderRes)
                .into(binding.eventImage)

            // Set click listener
            binding.root.setOnClickListener {
                onItemClick(event)
            }
        }

        // Placeholder image berdasarkan ID event
        private fun getPlaceholderImage(eventId: Int): Int {
            return when (eventId) {
                8938 -> R.drawable.cara_mencari_dan_melamar_pekerjaan_di_upwork
                8963 -> R.drawable.devcoach_174
                8958 -> R.drawable.idcamp_alumni_dialogue_1_customer_centric_development_and_user_experience
                8948 -> R.drawable.bootcamp
                8943 -> R.drawable.idcamp_x_dicoding_live_2_automation_fast_track_your_career
                8953 -> R.drawable.devkoch173
                8933 -> R.drawable.dosdevcoach_172
                8898 -> R.drawable.offline_event_baparekraf
                8928 -> R.drawable.devcoach_171_machine_learning_in_google
                8923 -> R.drawable.devcoach_170_data_science
                8918 -> R.drawable.idcamp_x_dicoding_live_1_beyond_the_basics_elevate_your_career_as_a_full_stack
                8908 -> R.drawable.devcoach_169
                8903 -> R.drawable.devcoach_168
                8893 -> R.drawable.devcoach_167
                8883 -> R.drawable.devcoach_166
                8878 -> R.drawable.devcoach_165
                8868 -> R.drawable.dicoding_ignite_path
                8863 -> R.drawable.devcoach_164
                8853 -> R.drawable.devcoach_163
                8848 -> R.drawable.devcoach_162
                8808 -> R.drawable.idcamp_x_dicoding_live_deep_learning
                8833 -> R.drawable.tech_meetup_berkarya
                8838 -> R.drawable.study_jam_laravel_authentication
                8828 -> R.drawable.dicoding_bootcamp_trial_session_4
                8823 -> R.drawable.devcoach_161
                8818 -> R.drawable.study_jam_laravel_laravel_rest_api_week_3
                8813 -> R.drawable.devcoach_160
                8798 -> R.drawable.study_jam_laravel_introduction_to_laravel_week_2
                8793 -> R.drawable.devcoach_159
                8778 -> R.drawable.study_jam_laravel_introduction_to_laravel_week_1
                8783 -> R.drawable.devcoach_158
                8788 -> R.drawable.dicoding_sharing_session_unlocking_creativity
                8747 -> R.drawable.building_performant_web_applications
                8772 -> R.drawable.devcoach_157
                8752 -> R.drawable.devcoach_156
                8742 -> R.drawable.integrate_gen_ai
                8727 -> R.drawable.devcoach_155
                8682 -> R.drawable.the_blueprint_for_android_app_success
                8712 -> R.drawable.webcode_complete
                8722 -> R.drawable.devcoach_154
                8702 -> R.drawable.wikidroid_ui_slicing
                8707 -> R.drawable.maximize_your_content_with_beautiful_assets
                8677 -> R.drawable.pkm_instiki_techfest_2024_
                8577 -> R.drawable.devcoach_153
                else -> R.drawable.error_image
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventFavouriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(eventList[position], onItemClick)
    }

    override fun getItemCount(): Int = eventList.size

    fun submitList(events: List<Event>) {
        this.eventList = events
        notifyDataSetChanged()
    }
}
