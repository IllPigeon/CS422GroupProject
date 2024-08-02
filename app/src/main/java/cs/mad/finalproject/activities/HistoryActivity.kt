package cs.mad.finalproject.activities

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cs.mad.finalproject.adapters.HistoryAdapter
import androidx.lifecycle.Observer
import cs.mad.finalproject.R
import cs.mad.finalproject.applications.DecisionApplication
import cs.mad.finalproject.databinding.ActivityHistoryBinding
import cs.mad.finalproject.mvc.HistoryViewModel
import cs.mad.finalproject.mvc.HistoryViewModelFactory

class HistoryActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        //call repository to get access to database to populate data
        val repository = (application as DecisionApplication).historyRepository
        val viewModelFactory = HistoryViewModelFactory(repository)
        historyViewModel = ViewModelProvider(this, viewModelFactory)[HistoryViewModel::class.java]

        //call adapter
        adapter = HistoryAdapter(historyViewModel)
        binding.historyRecycler.adapter = adapter
        binding.historyRecycler.layoutManager = LinearLayoutManager(this)

        historyViewModel.allHistory.observe(this, Observer { allHistory ->
            allHistory?.let {
                adapter.updateData(it)
            }
        })

        // swipe to delete
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = false
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }).attachToRecyclerView(binding.historyRecycler)
    }
}