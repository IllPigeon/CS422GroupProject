package cs.mad.week3lab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cs.mad.week3lab.entities.FlashcardSet
import cs.mad.week3lab.entities.getFlashcardSets

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.FlashCardSetRecyclerView)
        val flashCardSetAdapter = FlashcardSetAdapter()
        recyclerView.adapter = flashCardSetAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val addButton: Button = findViewById(R.id.add_set)

        addButton.setOnClickListener {
            flashCardSetAdapter.addSet()
        }

    }
}