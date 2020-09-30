package org.company.annamedvedieva.myplanktimer.planktimer

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import org.company.annamedvedieva.myplanktimer.R
import org.company.annamedvedieva.myplanktimer.data.PlankDatabase
import org.company.annamedvedieva.myplanktimer.databinding.FragmentPlankTimerBinding


class PlankTimerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentPlankTimerBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_plank_timer, container, false)

        val application = requireNotNull(this.activity).application

        val dao = PlankDatabase.getInstance(application).plankDao
        val viewModelFactory = PlankTimerViewModelFactory(dao)
        val plankTimerViewModel = ViewModelProvider(this, viewModelFactory).get(PlankTimerViewModel::class.java)

        binding.plankTimerViewModel = plankTimerViewModel

        binding.stopButton.setOnClickListener { view: View ->
            view.findNavController().navigate(PlankTimerFragmentDirections.actionPlankTimerFragmentToResultsFragment())
        }

        binding.setLifecycleOwner(this)

        plankTimerViewModel.plankType.observe(viewLifecycleOwner, Observer {
            Toast.makeText(application, it, Toast.LENGTH_LONG).show()
        })

        return binding.root
    }

}

