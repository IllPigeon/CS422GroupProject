package cs.mad.finalproject.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cs.mad.finalproject.R
import cs.mad.finalproject.adapters.DecisionAdapter
import cs.mad.finalproject.adapters.DecisionListAdapter
import cs.mad.finalproject.applications.DecisionApplication
import cs.mad.finalproject.databinding.ActivityMainBinding
import cs.mad.finalproject.entities.Decision
import cs.mad.finalproject.entities.History
import cs.mad.finalproject.mvc.DecisionViewModel
import cs.mad.finalproject.mvc.DecisionViewModelFactory
import cs.mad.finalproject.mvc.HistoryViewModel
import cs.mad.finalproject.mvc.HistoryViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var decisionViewModel: DecisionViewModel
    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var adapter: DecisionListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val historyButton: Button = findViewById(R.id.history_button)

        historyButton.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

        //call repository to gain access to database
        val repository = (application as DecisionApplication).decisionRepository
        val viewModelFactory = DecisionViewModelFactory(repository)
        decisionViewModel = ViewModelProvider(this, viewModelFactory)[DecisionViewModel::class.java]
        // History repository
        val historyRepository = (application as DecisionApplication).historyRepository
        val historyViewModelFactory = HistoryViewModelFactory(historyRepository)
        historyViewModel = ViewModelProvider(this, historyViewModelFactory)[HistoryViewModel::class.java]

        val addButton: Button = findViewById(R.id.add_button)
        addButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Create New Decision List")
            val input = EditText(this)
            builder.setView(input)

            builder.setPositiveButton("Create") { dialog, _ ->
                val newListName = input.text.toString()
                // Create a new DecisionList object with the entered name
                val newDecisionList: Decision = Decision(null, newListName, emptyList())
                val newHistory: History = History(null, newListName, newListName, emptyList())
                // Insert the new DecisionList into the database using the ViewModel
                decisionViewModel.insert(newDecisionList)
                historyViewModel.insert(newHistory)
            }
            builder.setNeutralButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            builder.show()
        }

        //TESTING BLOCK. DELETE BEFORE MERGING.
//        val historyRepository = (application as DecisionApplication).historyRepository
//        val historyViewModelFactory = HistoryViewModelFactory(historyRepository)
//        val historyViewModel = ViewModelProvider(this, historyViewModelFactory)[HistoryViewModel::class.java]
//
//        for (i in 1..20) {
//            val testList: List<String> = listOf(i.toString())
//            val newDecision: Decision = Decision(null, i.toString(), testList)
//            val newHistory: History = History(null, (i+1).toString(), i.toString(), testList)
//            decisionViewModel.insert(newDecision)
//            historyViewModel.insert(newHistory)
//        }

        //call adapter for recyclerview, pass in the view model so you have a way to access the database in it.
        adapter = DecisionListAdapter(decisionViewModel)
        binding.decisionSetRecycler.adapter = adapter
        binding.decisionSetRecycler.layoutManager = LinearLayoutManager(this)

        decisionViewModel.allDecisions.observe(this, Observer { allDecision ->
            allDecision?.let {
                adapter.updateData(it)
            }
        })

        //delete on swipe
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = false
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }).attachToRecyclerView(binding.decisionSetRecycler)
    }
}
