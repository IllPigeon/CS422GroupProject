package cs.mad.finalproject.mvc

import cs.mad.finalproject.entities.Decision
import cs.mad.finalproject.entities.DecisionDao
import kotlinx.coroutines.flow.Flow

class DecisionRepository(private val decisionDao: DecisionDao) {

    val allDecisions: Flow<List<Decision>> = decisionDao.getAll()

    suspend fun getSnapshot(): List<Decision> {
        return decisionDao.getSnapshot()
    }

    suspend fun insert(decision: Decision) {
        decisionDao.insert(decision)
    }

    suspend fun update(decision: Decision) {
        decisionDao.update(decision)
    }

    suspend fun delete(decision: Decision) {
        decisionDao.delete(decision)
    }

    suspend fun deleteAll() {
        decisionDao.deleteAll()
    }
}