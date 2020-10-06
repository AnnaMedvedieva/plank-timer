package org.company.annamedvedieva.myplanktimer.results

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.company.annamedvedieva.myplanktimer.data.Plank
import org.company.annamedvedieva.myplanktimer.data.PlankDao

class ResultsViewModel(val dao: PlankDao): ViewModel() {

    private var _snackBarDelete = MutableLiveData<Boolean?>()

    val snackBarDelete: LiveData<Boolean?>
    get() = _snackBarDelete

    private var _lastRemovedEntry = MutableLiveData<Plank?>()

    val planks: LiveData<List<Plank>> = dao.getAllPlanks()

    fun onDeleteClicked(plankId: Long) {
        viewModelScope.launch {
            _lastRemovedEntry.value = getEntryToRemove(plankId)
            deleteEntry(plankId)
            _snackBarDelete.value = true
        }
    }

    fun onUndoClicked(){
        viewModelScope.launch {
            addEntry(_lastRemovedEntry.value)
        }
    }

    private suspend fun deleteEntry(plankId: Long){
        dao.deletePlankById(plankId)
    }

    private suspend fun getEntryToRemove(plankId: Long): Plank?{
        return dao.get(plankId)
    }

    private suspend fun addEntry(plank: Plank?){
        dao.insert(plank)
    }

    fun doneShowingSnackbar(){
        _snackBarDelete.value = null
    }
}

