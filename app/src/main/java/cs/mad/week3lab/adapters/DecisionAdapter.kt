package cs.mad.week3lab.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cs.mad.week3lab.entities.Decision
import cs.mad.week3lab.mvc.DecisionViewModel

class DecisionAdapter(private val viewModel: DecisionViewModel): RecyclerView.Adapter<DecisionAdapter.ViewHolder>() {

    private val data = mutableListOf<Decision>()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>
    }
}