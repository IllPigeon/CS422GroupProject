package cs.mad.week3lab.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cs.mad.week3lab.data.daos.DecisionDao
import cs.mad.week3lab.data.databases.DecisionDatabase
import cs.mad.week3lab.data.entities.Decision
import cs.mad.week3lab.runOnIO
import cs.mad.week3lab.R
import cs.mad.week3lab.adapters.DecisionAdapter
import cs.mad.week3lab.databinding.ActivityDecisionsListBinding

class
DecisionActivity : AppCompatActivity() {
    // Viewbinding and database initialization
    private lateinit var binding: ActivityDecisionsListBinding
    private lateinit var db: DecisionDatabase
    private lateinit var decisionDao: DecisionDao

    override fun onCreate(savedInstanceState: Bundle?) {
        //Setting the binding here and view
        super.onCreate(savedInstanceState)
        binding = ActivityDecisionsListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // setting database and dao
        db = DecisionDatabase.getDatabase(this)
        decisionDao = db.DecisionDao()
        var currentDecisions: List<Decision> = listOf()
        // The intent used for getting the listId to retrieve the correct child decisions
        val listId = intent.getIntExtra("LIST_ID", -1)
        println("CURRENT INTENT LIST ID: $listId")
        runOnIO { currentDecisions = decisionDao.getByListId(listId) }

        //Setting the recycler view data alongside adapter
        val recyclerView = findViewById<RecyclerView>(R.id.FlashCardRecyclerView)
        val decisionAdapter = DecisionAdapter(currentDecisions)
        recyclerView.adapter = decisionAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Button logic for adding new decisions
        val addButton: Button = binding.addButton
        addButton.setOnClickListener {
            // list id very important for ensuring a decision is under the correct decision list parent
            val newDecision = Decision(name = "New Decision: ${decisionAdapter.itemCount}", listId = listId)
            runOnIO { decisionDao.insert(newDecision) }
            runOnUiThread { decisionAdapter.addDecision(newDecision) }
        }
    }
}