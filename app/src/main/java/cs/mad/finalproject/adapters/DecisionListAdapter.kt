package cs.mad.finalproject.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cs.mad.finalproject.R
import cs.mad.finalproject.data.entities.DecisionList


class DecisionListAdapter(decisionList: List<DecisionList>): RecyclerView.Adapter<DecisionListAdapter.ViewHolder>() {
    private val data = decisionList.toMutableList()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.title_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.flashcard_set_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val selectedDecisionList = data[position]
        holder.textView.text=selectedDecisionList.name
        // launches detail activity on click based on id of the set for the intent
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DecisionActivity::class.java)
            intent.putExtra("LIST_ID", selectedDecisionList.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        // how many items in list
        return data.size
    }

    fun addDecisionList(newDecisionList: DecisionList) {
        data.add(newDecisionList)
        notifyItemInserted(data.size - 1)
    }
}