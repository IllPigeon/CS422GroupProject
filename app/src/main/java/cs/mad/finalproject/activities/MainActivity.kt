package cs.mad.finalproject.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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
import cs.mad.finalproject.mvc.DecisionViewModel
import cs.mad.finalproject.mvc.DecisionViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var decisionViewModel: DecisionViewModel
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
