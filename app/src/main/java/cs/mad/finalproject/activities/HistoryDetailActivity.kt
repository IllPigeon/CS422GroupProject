package cs.mad.finalproject.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cs.mad.finalproject.adapters.HistoryAdapter
import cs.mad.finalproject.adapters.HistoryDetailAdapter
import cs.mad.finalproject.applications.DecisionApplication
import cs.mad.finalproject.databinding.ActivityHistoryDetailBinding
import cs.mad.finalproject.mvc.HistoryViewModel
import cs.mad.finalproject.mvc.HistoryViewModelFactory

class HistoryDetailActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHistoryDetailBinding
    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var adapter: HistoryDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = (application as DecisionApplication).historyRepository
        val viewModelFactory = HistoryViewModelFactory(repository)
        historyViewModel = ViewModelProvider(this, viewModelFactory)[HistoryViewModel::class.java]

        adapter = HistoryDetailAdapter(historyViewModel)
        binding.historyItemRecycler.adapter = adapter
        binding.historyItemRecycler.layoutManager = LinearLayoutManager(this)
    }
}