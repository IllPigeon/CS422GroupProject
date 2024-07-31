package cs.mad.finalproject.activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import cs.mad.finalproject.R
import cs.mad.finalproject.adapters.HistoryAdapter
import cs.mad.finalproject.adapters.HistoryDetailAdapter
import cs.mad.finalproject.applications.DecisionApplication
import cs.mad.finalproject.databinding.ActivityHistoryDetailBinding
import cs.mad.finalproject.entities.History
import cs.mad.finalproject.mvc.HistoryViewModel
import cs.mad.finalproject.mvc.HistoryViewModelFactory
import kotlinx.coroutines.launch

class HistoryDetailActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHistoryDetailBinding
    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var adapter: HistoryDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val repository = (application as DecisionApplication).historyRepository
        val viewModelFactory = HistoryViewModelFactory(repository)
        historyViewModel = ViewModelProvider(this, viewModelFactory)[HistoryViewModel::class.java]

        adapter = HistoryDetailAdapter(historyViewModel)
        binding.historyItemRecycler.adapter = adapter
        binding.historyItemRecycler.layoutManager = LinearLayoutManager(this)

        val historyId = intent.getLongExtra("history_id", -1)

        lifecycleScope.launch {
           try {
               val chosenHistory = historyViewModel.getHistoryById(historyId)
               val setTitle: TextView = findViewById(R.id.decision_set_title)
               val decisionMade: TextView = findViewById(R.id.decision_made)
               setTitle.text = chosenHistory.setTitle
               decisionMade.text = chosenHistory.decisionMade
               adapter.updateData(chosenHistory.optionsSnapshot)
           } catch(e: Exception) {
              e.printStackTrace()
              finish()
           }
        }
    }
}