package cs.mad.finalproject.mvc

import cs.mad.finalproject.entities.History
import cs.mad.finalproject.entities.HistoryDao
import kotlinx.coroutines.flow.Flow

class HistoryRepository(private val historyDao: HistoryDao) {

    val allHistory: Flow<List<History>> = historyDao.getAll()

    suspend fun getHistoryById(historyId: Long): History {
        return historyDao.getHistoryById(historyId)
    }

    suspend fun insert(history: History) {
        historyDao.insert(history)
    }

    suspend fun delete(history: History){
        historyDao.delete(history)
    }

    suspend fun deleteAll() {
        historyDao.deleteAll()
    }
}