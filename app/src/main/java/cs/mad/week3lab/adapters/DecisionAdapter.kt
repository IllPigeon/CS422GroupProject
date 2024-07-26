package cs.mad.week3lab.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cs.mad.week3lab.R
import cs.mad.week3lab.data.entities.Decision

class DecisionAdapter(decisions: List<Decision>): RecyclerView.Adapter<DecisionAdapter.ViewHolder>() {
    private val data = decisions.toMutableList()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val viewTerm = view.findViewById<TextView>(R.id.term_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.flashcard_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // connect data to view
        val item = data[position]
        holder.viewTerm.text=item.name
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addDecision(newDecision: Decision) {
       data.add(newDecision)
       notifyItemInserted(data.size - 1)
    }
}