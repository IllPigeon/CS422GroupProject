package cs.mad.week3lab

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cs.mad.week3lab.entities.FlashcardSet
import cs.mad.week3lab.entities.getFlashcardSets

class FlashcardSetAdapter: RecyclerView.Adapter<FlashcardSetAdapter.ViewHolder>() {
    private val data = getFlashcardSets().toMutableList()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.title_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.flashcard_set_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // launches detail activity on click
        holder.itemView.setOnClickListener {
            it.context.startActivity(Intent(it.context, FlashcardSetDetailActivity::class.java))
        }
        val item = data[position]
        holder.textView.text=item.title
    }

    override fun getItemCount(): Int {
        // how many items in list
        return data.size
    }

    fun addSet() {
        data.add(FlashcardSet("New Added Title"))
        notifyItemInserted(data.size - 1)
    }
}