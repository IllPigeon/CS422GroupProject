package cs.mad.week3lab.mvc

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import cs.mad.week3lab.entities.Decision
import kotlinx.coroutines.launch

class DecisionViewModel(private val repository: DecisionRepository): ViewModel() {
    val allDecisions: LiveData<List<Decision>> = repository.allDecisions.asLiveData()

    fun insert(decision: Decision) = viewModelScope.launch {
        repository.insert(decision)
    }

    fun delete(decision: Decision) = viewModelScope.launch {
        repository.delete(decision)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
}

class DecisionViewModelFactory(private val repository: DecisionRepository) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(DecisionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DecisionViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}