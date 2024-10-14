package com.example.aplikasievent.ui.Finished

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplikasievent.Event
import com.example.aplikasievent.databinding.ItemEventFinishedBinding

class FinishedAdapter : RecyclerView.Adapter<FinishedAdapter.EventViewHolder>() {

    private var eventList: List<Event> = listOf()

    class EventViewHolder(private val binding: ItemEventFinishedBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event) {
            binding.eventName.text = event.name
            Glide.with(binding.eventImage.context)
                .load(event.imageUrl)
                .into(binding.eventImage)

            // Set click listener on the item view
            binding.root.setOnClickListener {
                // Menggunakan NavController untuk menavigasi dan mengirim ID event ke DetailFragment
                val action = FinishedFragmentDirections.actionNavigationFinishedToDetailFragmentFinished(event.id)
                it.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventFinishedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
