package org.company.annamedvedieva.myplanktimer.planktimer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.company.annamedvedieva.myplanktimer.data.PlankDao

class PlankTimerViewModelFactory(
    private val dao: PlankDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlankTimerViewModel::class.java)) {
            return PlankTimerViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}