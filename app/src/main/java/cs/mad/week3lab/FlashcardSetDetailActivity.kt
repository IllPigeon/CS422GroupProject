package cs.mad.week3lab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import cs.mad.week3lab.entities.getFlashcards

class
FlashcardSetDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashcard_set_detail)

        val recyclerView = findViewById<RecyclerView>(R.id.FlashCardRecyclerView)
        val flashCardAdapter = FlashcardAdapter()
        recyclerView.adapter = flashCardAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val addButton: Button = findViewById(R.id.add_button)

        addButton.setOnClickListener {
            flashCardAdapter.addFlashcard()
        }
    }
}