package cs.mad.finalproject.applications

import android.app.Application
import cs.mad.finalproject.databases.DecisionDatabase
import cs.mad.finalproject.databases.HistoryDatabase
import cs.mad.finalproject.mvc.DecisionRepository
import cs.mad.finalproject.mvc.HistoryRepository

class DecisionApplication : Application() {
    val historyDatabase by lazy { HistoryDatabase.getDatabase(this) }
    val historyRepository by lazy { HistoryRepository(historyDatabase.historyDao()) }

    val decisionDatabase by lazy { DecisionDatabase.getDatabase(this) }
    val decisionRepository by lazy { DecisionRepository(decisionDatabase.decisionDao()) }
}