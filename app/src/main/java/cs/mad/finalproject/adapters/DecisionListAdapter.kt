package cs.mad.finalproject.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cs.mad.finalproject.R
import cs.mad.finalproject.activities.DecisionDetailActivity
import cs.mad.finalproject.adapters.DecisionAdapter.ViewHolder
import cs.mad.finalproject.entities.Decision
import cs.mad.finalproject.mvc.DecisionViewModel


class DecisionListAdapter(private val viewModel: DecisionViewModel): RecyclerView.Adapter<DecisionListAdapter.ViewHolder>() {
    private val data = mutableListOf<Decision>()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.decision_set_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.decision_set_item, parent, false)
    )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val decision = data[position]
        holder.textView.text = data[position].title
        holder.itemView.setOnClickListener {
            val context = it.context
            val intent = Intent(context, DecisionDetailActivity::class.java).apply {
                putExtra("decision_id", decision.id)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        // how many items in list
        return data.size
    }

    fun updateData(newData: List<Decision>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    fun addDecisionList(newDecisionList: Decision) {
        data.add(newDecisionList)
        viewModel.insert(newDecisionList)
        notifyItemInserted(data.size - 1)
    }

    fun removeAt(index: Int) {
        viewModel.delete(data[index])
        data.removeAt(index)
        notifyDataSetChanged()
    }
}