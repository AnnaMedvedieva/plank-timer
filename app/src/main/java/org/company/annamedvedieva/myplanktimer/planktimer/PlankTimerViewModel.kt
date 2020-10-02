package org.company.annamedvedieva.myplanktimer.planktimer

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.Chronometer
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.company.annamedvedieva.myplanktimer.data.PlankDao
import java.text.SimpleDateFormat
import java.util.*


class PlankTimerViewModel(val dao: PlankDao): ViewModel() {

    private var _plankType = MutableLiveData<String?>()

    private var _dateToday = MutableLiveData<String?>()

    val plankType:LiveData<String?>
    get() = _plankType

    private var _timeInPlank = MutableLiveData<String?>()

    fun createEntry(plankTime: String){
        _timeInPlank.value = plankTime
        _dateToday.value = getCurrentDate()

       // addNewPlankEntry(_plankType, _timeInPlank, _dateToday)
    }

    private fun getCurrentDate(): String {
        val date= Calendar.getInstance().time
        val simpleDateFormat = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
        return simpleDateFormat.format(date)
    }
    
    //Gets plankType from Spinner
    val onSelectItem = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            _plankType.value = parent.getItemAtPosition(position) as String
        }
        override fun onNothingSelected(parent: AdapterView<*>?) {
            _plankType.value = "Low plank"
        }
    }


}