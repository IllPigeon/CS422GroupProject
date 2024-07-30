package cs.mad.finalproject.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cs.mad.finalproject.entities.Decision
import cs.mad.finalproject.mvc.DecisionViewModel

class DecisionAdapter(private val viewModel: DecisionViewModel): RecyclerView.Adapter<DecisionAdapter.ViewHolder>() {

    private val data = mutableListOf<Decision>()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>
    }
}