package org.company.annamedvedieva.myplanktimer.results

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import org.company.annamedvedieva.myplanktimer.R
import org.company.annamedvedieva.myplanktimer.data.PlankDatabase
import org.company.annamedvedieva.myplanktimer.databinding.FragmentResultsBinding
import org.company.annamedvedieva.myplanktimer.planktimer.PlankTimerViewModel

class ResultsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentResultsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_results, container, false)

        val application = requireNotNull(this.activity).application
        val dao = PlankDatabase.getInstance(application).plankDao

        val factory = ResultsViewModelFactory(dao)

        val resultsViewModel = ViewModelProvider(this, factory).get(ResultsViewModel::class.java)

        binding.resultsViewModel = resultsViewModel

        binding.setLifecycleOwner(this)

        return binding.root
    }


}