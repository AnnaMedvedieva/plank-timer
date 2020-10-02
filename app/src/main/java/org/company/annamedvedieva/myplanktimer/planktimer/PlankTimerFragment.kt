package org.company.annamedvedieva.myplanktimer.planktimer

import android.app.Application
import android.opengl.Visibility
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_plank_timer.*
import org.company.annamedvedieva.myplanktimer.R
import org.company.annamedvedieva.myplanktimer.data.PlankDatabase
import org.company.annamedvedieva.myplanktimer.databinding.FragmentPlankTimerBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class PlankTimerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentPlankTimerBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_plank_timer, container, false)

        val application = requireNotNull(this.activity).application

        //TODO: remove this temp code
        val chronometer = binding.plankChronometer




        val dao = PlankDatabase.getInstance(application).plankDao
        val viewModelFactory = PlankTimerViewModelFactory(dao)
        val plankTimerViewModel = ViewModelProvider(this, viewModelFactory).get(PlankTimerViewModel::class.java)

        binding.plankTimerViewModel = plankTimerViewModel

        binding.startButton.setOnClickListener { view:View ->
            chronometer.setBase(SystemClock.elapsedRealtime())
            chronometer.start()
            view.visibility = View.INVISIBLE
            stopButton.visibility = View.VISIBLE
            resetButton.visibility = View.VISIBLE
        }

        binding.stopButton.setOnClickListener { view: View ->
            val savedTime = SystemClock.elapsedRealtime() - chronometer.getBase()
            chronometer.stop()
            plankTimerViewModel.createEntry(convertMillis(savedTime))
            view.findNavController().navigate(PlankTimerFragmentDirections.actionPlankTimerFragmentToResultsFragment())
        }

        binding.resetButton.setOnClickListener { view: View ->
            chronometer.setBase(SystemClock.elapsedRealtime())
            chronometer.stop()
            view.visibility = View.INVISIBLE
            stopButton.visibility = View.VISIBLE
            startButton.visibility = View.VISIBLE

        }

        binding.setLifecycleOwner(this)

        plankTimerViewModel.plankType.observe(viewLifecycleOwner, Observer {
            Toast.makeText(application, it, Toast.LENGTH_LONG).show()
        })

        return binding.root
    }

    fun convertMillis(millisTime: Long): String {
        //val seconds = (millisTime/1000) % 60
        val seconds = TimeUnit.MILLISECONDS.toSeconds(millisTime)
        val minutes = (millisTime/1000) / 60
        return "$minutes min $seconds s"

    }

}

