package cs.mad.week3lab.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cs.mad.week3lab.R
import cs.mad.week3lab.activities.DecisionActivity
import cs.mad.week3lab.data.entities.DecisionList


class DecisionListAdapter(decisionList: List<DecisionList>): RecyclerView.Adapter<DecisionListAdapter.ViewHolder>() {
    private val data = decisionList.toMutableList()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.title_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.flashcard_set_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // launches detail activity on click
        holder.itemView.setOnClickListener {
            it.context.startActivity(Intent(it.context, DecisionActivity::class.java))
        }
        val item = data[position]
        holder.textView.text=item.name
    }

    override fun getItemCount(): Int {
        // how many items in list
        return data.size
    }

    fun addDecisionList() {
        data.add(DecisionList(data.size + 1, "New Decision List"))
        notifyItemInserted(data.size - 1)
    }
}