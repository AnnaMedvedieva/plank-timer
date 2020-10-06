package org.company.annamedvedieva.myplanktimer.results

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
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


        val adapter = PlanksListAdapter(PlankListener {plankId ->
            resultsViewModel.onDeleteClicked(plankId)
        })

        binding.plankList.adapter = adapter

        resultsViewModel.planks.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        resultsViewModel.snackBarDelete.observe(viewLifecycleOwner, Observer {
            if(it == true){
                Snackbar.make(requireActivity().findViewById(android.R.id.content),
                getString(R.string.snackbar_delete),
                Snackbar.LENGTH_SHORT).show()

                resultsViewModel.doneShowingSnackbar()
            }

        })

        binding.setLifecycleOwner(this)

        return binding.root
    }


}