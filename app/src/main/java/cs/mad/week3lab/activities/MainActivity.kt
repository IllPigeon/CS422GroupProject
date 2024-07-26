package cs.mad.week3lab.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import cs.mad.week3lab.R
import cs.mad.week3lab.adapters.DecisionListAdapter
import cs.mad.week3lab.data.daos.DecisionListDao
import cs.mad.week3lab.data.databases.DecisionListDatabase
import cs.mad.week3lab.data.entities.DecisionList
import cs.mad.week3lab.runOnIO

class MainActivity : AppCompatActivity() {
    private lateinit var db: DecisionListDatabase
    private lateinit var decisionListDao: DecisionListDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setting database and dao
        db = DecisionListDatabase.getDatabase(this)
        decisionListDao = db.decisionListDao()
        var currentDecisionList: List<DecisionList> = listOf()
        runOnIO { currentDecisionList = decisionListDao.getAll() }


        val recyclerView = findViewById<RecyclerView>(R.id.FlashCardSetRecyclerView)
        val decisionListAdapter = DecisionListAdapter(currentDecisionList)
        recyclerView.adapter = decisionListAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val addButton: Button = findViewById(R.id.add_set)

        addButton.setOnClickListener {
            decisionListAdapter.addDecisionList()
        }
    }
}