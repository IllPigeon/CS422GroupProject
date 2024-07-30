package cs.mad.finalproject.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cs.mad.finalproject.databinding.ActivityHistoryBinding
import cs.mad.finalproject.mvc.HistoryViewModel

class HistoryActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}