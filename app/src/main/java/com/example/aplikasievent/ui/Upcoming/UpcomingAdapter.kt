package com.example.aplikasievent.ui.Upcoming

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplikasievent.Event
import com.example.aplikasievent.R
import com.example.aplikasievent.databinding.ItemEventUpcomingBinding

class UpcomingAdapter(private val onItemClick: (Event) -> Unit) : RecyclerView.Adapter<UpcomingAdapter.EventViewHolder>() {

    private var eventList: List<Event> = listOf()

    class EventViewHolder(private val binding: ItemEventUpcomingBinding, val onItemClick: (Event) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event, position: Int) {
            binding.eventName.text = event.name

            val placeholderRes = getPlaceholderImage(position)

            Glide.with(binding.eventImage.context)
                .load(event.imageUrl)
                .placeholder(placeholderRes)
                .into(binding.eventImage)

            binding.root.setOnClickListener {
                val action = UpcomingFragmentDirections.actionNavigationUpcomingToDetailFragmentUpcoming(event.id)
                it.findNavController().navigate(action)
            }
        }

        private fun getPlaceholderImage(position: Int): Int {
            return when (position % 38 + 1) {
                1 -> R.drawable.bootcamp
                2 -> R.drawable.devkoch173
                else -> R.drawable.error_image
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventUpcomingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding, onItemClick)
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
