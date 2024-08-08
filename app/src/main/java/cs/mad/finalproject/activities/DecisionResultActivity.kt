package cs.mad.finalproject.activities


//do I have to use decisiondb or decisiondao here?
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import cs.mad.finalproject.R
import cs.mad.finalproject.applications.DecisionApplication
import cs.mad.finalproject.databases.DecisionDatabase
import cs.mad.finalproject.databinding.ActivityDecisionResultBinding
import cs.mad.finalproject.entities.DecisionDao
import cs.mad.finalproject.entities.History
import cs.mad.finalproject.mvc.HistoryViewModel
import cs.mad.finalproject.mvc.HistoryViewModelFactory
import kotlinx.coroutines.launch

class DecisionResultActivity : AppCompatActivity() {

    //Binding creation
    //not sure if I should use DecisionDao or DecisionListDao or both ???
    //since this page should display the random decision from the decision list chosen
    private lateinit var binding: ActivityDecisionResultBinding
    private lateinit var historyViewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDecisionResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val historyButton: Button = findViewById(R.id.history_button)
        historyButton.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }


        val repository = (application as DecisionApplication).historyRepository
        val viewModelFactory = HistoryViewModelFactory(repository)
        historyViewModel = ViewModelProvider(this, viewModelFactory)[HistoryViewModel::class.java]

        //trying to get intent details

        val decisionMade = intent.getStringExtra("decision_made")?: ""
        val setTitle = intent.getStringExtra("set_title")?: ""
        val optionsSnapshot = intent.getStringArrayListExtra("options_snapshot")?.toList() ?: listOf()

        //display??
        val displayDecision = "The option I choose for you is: $decisionMade"
        binding.decisionTextView.text = displayDecision

        //save to history db?
        saveDecision(decisionMade, setTitle, optionsSnapshot)

    }

    //trying to automatically save it to history db
    //or should I add a button to do this?
    //is this correct?
    private fun saveDecision(decisionMade: String, setTitle: String, optionsSnapshot: List<String>) {

        val history = History(decisionMade = decisionMade, setTitle = setTitle, optionsSnapshot = optionsSnapshot)
        lifecycleScope.launch {
            historyViewModel.insert(history)
        }

    }
}