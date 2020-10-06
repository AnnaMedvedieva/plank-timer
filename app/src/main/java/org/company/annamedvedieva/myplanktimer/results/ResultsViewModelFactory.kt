package org.company.annamedvedieva.myplanktimer.results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.company.annamedvedieva.myplanktimer.data.PlankDao

class ResultsViewModelFactory(
    private val dao: PlankDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultsViewModel::class.java)) {
            return ResultsViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}