package org.company.annamedvedieva.myplanktimer.planktimer


import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.company.annamedvedieva.myplanktimer.data.Plank
import org.company.annamedvedieva.myplanktimer.data.PlankDao
import java.text.SimpleDateFormat
import java.util.*


class PlankTimerViewModel(val dao: PlankDao): ViewModel() {

    private var _plankType = MutableLiveData<String?>()

    val plankType:LiveData<String?>
    get() = _plankType

    fun createEntry(plankTime: String){
        val newPlankEntry = Plank()
        newPlankEntry.date = getCurrentDate()
        newPlankEntry.duration = plankTime
        newPlankEntry.plankType = _plankType.value

        viewModelScope.launch {
            addNewPlankEntry(newPlankEntry)
        }
    }

    private suspend fun addNewPlankEntry(plank:Plank){
        dao.insert(plank)
    }

    // Date formatting
    private fun getCurrentDate(): String {
        val date= Calendar.getInstance().time
        val simpleDateFormat = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
        return simpleDateFormat.format(date)
    }

    // Gets plankType from Spinner
    val onSelectItem = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            _plankType.value = parent.getItemAtPosition(position) as String
        }
        override fun onNothingSelected(parent: AdapterView<*>?) {
            _plankType.value = "Low plank"
        }
    }


}