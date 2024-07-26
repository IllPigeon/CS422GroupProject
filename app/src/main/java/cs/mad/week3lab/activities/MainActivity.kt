package cs.mad.week3lab.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import cs.mad.week3lab.adapters.DecisionListAdapter
import cs.mad.week3lab.data.daos.DecisionListDao
import cs.mad.week3lab.data.databases.DecisionListDatabase
import cs.mad.week3lab.data.entities.DecisionList
import cs.mad.week3lab.databinding.ActivityMainBinding
import cs.mad.week3lab.runOnIO

class MainActivity : AppCompatActivity() {
    // Using viewbinding and initializing the database and dao
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: DecisionListDatabase
    private lateinit var decisionListDao: DecisionListDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // setting database and dao
        db = DecisionListDatabase.getDatabase(this)
        decisionListDao = db.decisionListDao()
        var currentDecisionList: List<DecisionList> = listOf()
        runOnIO { currentDecisionList = decisionListDao.getAll() }

        // setting up recycler view and adapter
        val recyclerView = binding.FlashCardSetRecyclerView
        val decisionListAdapter = DecisionListAdapter(currentDecisionList)
        recyclerView.adapter = decisionListAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 1)

        // Button logic to add decision list with background thread helper and UI thread to update recyclerview
        val addButton: Button = binding.addSet
        addButton.setOnClickListener {
            val newDecisionList = DecisionList(name = "New Decision List: ${decisionListAdapter.itemCount}")
            runOnIO {
                //Used to help with updating the current id of newly created decision list in that lifecycle
                val newDecisionListId = decisionListDao.insert(newDecisionList).toInt()
                val updatedDecisionList = newDecisionList.copy(id = newDecisionListId)
                runOnUiThread { decisionListAdapter.addDecisionList(updatedDecisionList) }
            }
        }
    }
}