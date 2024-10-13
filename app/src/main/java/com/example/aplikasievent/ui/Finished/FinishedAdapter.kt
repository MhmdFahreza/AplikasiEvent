import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplikasievent.Event
import com.example.aplikasievent.databinding.FragmentFinishedBinding

class FinishedAdapter : RecyclerView.Adapter<FinishedAdapter.EventViewHolder>() {

    private var eventList: List<Event> = listOf()

    class EventViewHolder(private val binding: FragmentFinishedBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event) {
            binding.eventNameFinished.text = event.name
            Glide.with(binding.eventImage.context)
                .load(event.imageUrl) // Load the event image using Glide
                .into(binding.eventImage) // Set the image in the ImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = FragmentFinishedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
