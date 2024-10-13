package com.example.aplikasievent.ui.Upcoming

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplikasievent.Event
import com.example.aplikasievent.databinding.ItemEventUpcomingBinding

class UpcomingAdapter : RecyclerView.Adapter<UpcomingAdapter.EventViewHolder>() {

    private var eventList: List<Event> = listOf()

    class EventViewHolder(private val binding: ItemEventUpcomingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event) {
            binding.eventName.text = event.name
            Glide.with(binding.eventImage.context)
                .load(event.imageUrl)
                .into(binding.eventImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventUpcomingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(eventList[position])
    }

    override fun getItemCount(): Int = eventList.size

    fun submitList(events: List<Event>) {
        this.eventList = events
        notifyDataSetChanged()
    }
}
