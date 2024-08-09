package cs.mad.finalproject.activities

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import android.widget.EditText
import androidx.annotation.IntRange
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cs.mad.finalproject.R
import cs.mad.finalproject.adapters.DecisionAdapter
import cs.mad.finalproject.applications.DecisionApplication
import cs.mad.finalproject.databinding.ActivityDecisionDetailBinding
import cs.mad.finalproject.entities.Decision
import cs.mad.finalproject.mvc.DecisionViewModel
import cs.mad.finalproject.mvc.DecisionViewModelFactory
import kotlinx.coroutines.launch

class DecisionDetailActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDecisionDetailBinding
    private lateinit var decisionViewModel: DecisionViewModel
    private lateinit var adapter: DecisionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDecisionDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = (application as DecisionApplication).decisionRepository
        val viewModelFactory = DecisionViewModelFactory(repository)
        decisionViewModel = ViewModelProvider(this, viewModelFactory)[DecisionViewModel::class.java]

        adapter = DecisionAdapter(decisionViewModel)
        binding.decisionRecycler.adapter = adapter
        binding.decisionRecycler.layoutManager = LinearLayoutManager(this)

        //grab the id for the chosen Decision (what the user clicked on before entering this activity)
        val decisionId = intent.getLongExtra("decision_id", -1)

        lifecycleScope.launch {
           try {
               //try to get the decision that they chose from the database
               val chosenDecision = decisionViewModel.getDecisionById(decisionId)
               val setTitle: TextView = findViewById(R.id.decision_set_title)

               setTitle.text = chosenDecision.title
               adapter.updateData(chosenDecision.options)
           } catch(e: Exception) {
               e.printStackTrace()
               finish()
           }
        }

        // Create new option button
        val addButton: Button = findViewById(R.id.add_decision_button)
        addButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Create New Option")
            val input = EditText(this)
            builder.setView(input)

            builder.setPositiveButton("Create") { dialog, _ ->
                val newOptionName = input.text.toString()
                lifecycleScope.launch {
                    try {
                        // Chosen Decision retrieved based on intent id
                        val chosenDecision = decisionViewModel.getDecisionById(decisionId)
                        // Retrieving the options based on chosenDecisionId
                        val updatedOptions = chosenDecision.options.toMutableList()
                        updatedOptions.add(newOptionName)
                        decisionViewModel.updateOptions(updatedOptions, decisionId)
                        //Updating adapter with new options here
                        adapter.updateData(updatedOptions)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            builder.setNeutralButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            builder.show()
        }

        // Randomly choose decision from list and show decision chosen
        val decisionButton: Button = findViewById(R.id.make_decision_button)
        decisionButton.setOnClickListener {
            lifecycleScope.launch{
                try {
                    val chosenDecisionList = decisionViewModel.getDecisionById(decisionId)
                    val randomDecisionNumber: Int = IntRange(start = 0, endInclusive = chosenDecisionList.options.size-1).random()
                    val decisionSelected = chosenDecisionList.options[randomDecisionNumber]
                    val decisionSet = chosenDecisionList.title
                    val optionsSnapshot = chosenDecisionList.options.toCollection(ArrayList())

                    val intent = Intent(this@DecisionDetailActivity, DecisionResultActivity::class.java).apply {
                        putExtra("decision_made", decisionSelected)
                        putExtra("set_title", decisionSet)
                        putStringArrayListExtra("options_snapshot", optionsSnapshot)
                    }
                    startActivity(intent)

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        val backButton: Button = findViewById(R.id.back_button)

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //delete on swipe
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = false
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.removeAt(viewHolder.adapterPosition, decisionId)
            }
        }).attachToRecyclerView(binding.decisionRecycler)
    }
}