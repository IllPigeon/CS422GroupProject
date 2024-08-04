package cs.mad.finalproject.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import cs.mad.finalproject.R
import cs.mad.finalproject.activities.DecisionDetailActivity
import cs.mad.finalproject.entities.Decision
import cs.mad.finalproject.mvc.DecisionViewModel
import android.widget.EditText

class DecisionAdapter(private val viewModel: DecisionViewModel): RecyclerView.Adapter<DecisionAdapter.ViewHolder>() {
    private val data = mutableListOf<String>()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.decision_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.decision_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = data[position]

        // Edit decision
        holder.itemView.setOnClickListener {
            val editDecision: EditText = EditText(it.context)
            editDecision.setText(holder.textView.text)

            val alert = AlertDialog.Builder(it.context)
                .setCustomTitle(editDecision)
                .setNeutralButton("Cancel") { dialog,_ ->
                    dialog.dismiss()
                }
                .setPositiveButton("Save") { dialog,_ ->
                    data[position] = editDecision.text.toString()
                    dialog.dismiss()
                }
                .create()
            alert.show()

            // NOT SURE WHY, BUT THIS DOES NOT SAVE THE UPDATE CORRECTLY
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = data.size

    fun addData() {
        data.add("New option")
        notifyDataSetChanged()
    }

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