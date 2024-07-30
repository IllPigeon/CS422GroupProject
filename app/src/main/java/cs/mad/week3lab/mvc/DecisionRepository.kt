package cs.mad.week3lab.mvc

import cs.mad.week3lab.entities.Decision
import cs.mad.week3lab.entities.DecisionDao
import kotlinx.coroutines.flow.Flow

class DecisionRepository(private val decisionDao: DecisionDao) {

    val allDecisions: Flow<List<Decision>> = decisionDao.getAll()

    suspend fun insert(decision: Decision) {
        decisionDao.insert(decision)
    }

    suspend fun delete(decision: Decision) {
        decisionDao.delete(decision)
    }

    suspend fun deleteAll() {
        decisionDao.deleteAll()
    }
}