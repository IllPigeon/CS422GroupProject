package cs.mad.finalproject.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cs.mad.finalproject.R
import cs.mad.finalproject.activities.HistoryDetailActivity
import cs.mad.finalproject.entities.History
import cs.mad.finalproject.mvc.HistoryViewModel

class HistoryAdapter(private val viewModel: HistoryViewModel): RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    private val data = mutableListOf<History>()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.decision_made)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val history = data[position]
        holder.textView.text = data[position].decisionMade
        holder.itemView.setOnClickListener {
            val context = it.context
            val intent = Intent(context, HistoryDetailActivity::class.java).apply {
                putExtra("history_id", history.id)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = data.size

    fun updateData(newData: List<History>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    fun removeAt(index: Int) {
        data.removeAt(index)
        viewModel.delete(data[index])
        notifyDataSetChanged()
    }
}