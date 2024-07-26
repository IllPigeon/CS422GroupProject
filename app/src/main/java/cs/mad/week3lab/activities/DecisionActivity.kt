package cs.mad.week3lab.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cs.mad.week3lab.data.daos.DecisionDao
import cs.mad.week3lab.data.daos.DecisionListDao
import cs.mad.week3lab.data.databases.DecisionDatabase
import cs.mad.week3lab.data.databases.DecisionListDatabase
import cs.mad.week3lab.data.entities.Decision
import cs.mad.week3lab.runOnIO
import cs.mad.week3lab.R

class
DecisionActivity : AppCompatActivity() {
    private lateinit var db: DecisionDatabase
    private lateinit var decisionDao: DecisionDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashcard_set_detail)

        db = DecisionDatabase.getDatabase(this)
        decisionDao = db.DecisionDao()

        var currentDecisions: List<Decision> = listOf()

        runOnIO { currentDecisions = decisionDao.getAll() }

        val recyclerView = findViewById<RecyclerView>(R.id.FlashCardRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val addButton: Button = findViewById(R.id.add_button)

        addButton.setOnClickListener {
            //add decision here
        }
    }
}