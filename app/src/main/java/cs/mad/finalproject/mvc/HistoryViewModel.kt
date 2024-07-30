package cs.mad.finalproject.mvc

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import cs.mad.finalproject.entities.History
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: HistoryRepository): ViewModel() {
    val allHistory: LiveData<List<History>> = repository.allHistory.asLiveData()

    fun insert(history: History) = viewModelScope.launch {
        repository.insert(history)
    }

    fun delete(history: History) = viewModelScope.launch {
        repository.delete(history)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
}

class HistoryViewModelFactory(private val repository: HistoryRepository) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HistoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}