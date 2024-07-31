package cs.mad.finalproject.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cs.mad.finalproject.R
import cs.mad.finalproject.activities.DecisionDetailActivity
import cs.mad.finalproject.entities.Decision
import cs.mad.finalproject.mvc.DecisionViewModel

class DecisionAdapter(private val viewModel: DecisionViewModel): RecyclerView.Adapter<DecisionAdapter.ViewHolder>() {
    private val data = mutableListOf<String>()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.decision_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.decision_set_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = data[position]
    }

    override fun getItemCount() = data.size

    fun updateData(newData: List<String>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    fun removeAt(index: Int, decisionId: Long) {
        data.removeAt(index)
        viewModel.updateOptions(data, decisionId)
        notifyDataSetChanged()
    }
}