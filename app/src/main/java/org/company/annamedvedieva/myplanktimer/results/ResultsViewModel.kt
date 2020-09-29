package org.company.annamedvedieva.myplanktimer.results

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.company.annamedvedieva.myplanktimer.data.PlankDao

class ResultsViewModel(dao: PlankDao): ViewModel() {

    private var _plankType = MutableLiveData<String?>()

    val plank: LiveData<String?>
    get() = _plankType

    fun onPlankTypeClick(type: String){
        _plankType.value = type
        }
}

