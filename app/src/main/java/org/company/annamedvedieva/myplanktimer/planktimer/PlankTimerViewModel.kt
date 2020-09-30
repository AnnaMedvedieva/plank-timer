package org.company.annamedvedieva.myplanktimer.planktimer

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.company.annamedvedieva.myplanktimer.data.PlankDao


class PlankTimerViewModel(val dao: PlankDao): ViewModel() {

    private var _plankType = MutableLiveData<String?>()

    val plankType:LiveData<String?>
    get() = _plankType

    val onSelectItem = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

        override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            _plankType.value = parent.getItemAtPosition(position) as String
        }
    }
}