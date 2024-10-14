import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplikasievent.Event
import com.example.aplikasievent.R
import com.example.aplikasievent.databinding.ItemEventFinishedBinding
import com.example.aplikasievent.ui.Finished.FinishedFragmentDirections


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
            return when (position % 38 + 1) { // Adjust position to start from 1 for easier readability
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
