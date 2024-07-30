package cs.mad.week3lab.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cs.mad.week3lab.databinding.ActivityHistoryBinding
import cs.mad.week3lab.mvc.DecisionViewModel

class HistoryActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var decisionViewModel: DecisionViewModel
    private lateinit var adapter: DecisionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}