package cs.mad.finalproject.activities

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
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

        //button functionality
        val addButton: Button = findViewById(R.id.add_decision_button)

        addButton.setOnClickListener {
            adapter.addData()
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