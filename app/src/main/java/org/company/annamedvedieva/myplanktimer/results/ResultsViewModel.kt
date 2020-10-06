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

    val planks: LiveData<List<Plank>> = dao.getAllPlanks()

    fun onDeleteClicked(plankId: Long) {
        viewModelScope.launch {
            deleteEntry(plankId)
            _snackBarDelete.value = true
        }
    }

    private suspend fun deleteEntry(plankId: Long){
        dao.deletePlankById(plankId)
    }

    fun doneShowingSnackbar(){
        _snackBarDelete.value = null
    }


}

