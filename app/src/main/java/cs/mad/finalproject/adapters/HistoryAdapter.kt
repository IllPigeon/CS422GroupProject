package cs.mad.finalproject.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cs.mad.finalproject.R
import cs.mad.finalproject.entities.History

class HistoryAdapter: RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    private val data = mutableListOf<History>()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.decision_made)
    }
}