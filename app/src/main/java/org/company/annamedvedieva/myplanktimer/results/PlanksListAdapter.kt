package org.company.annamedvedieva.myplanktimer.results

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.company.annamedvedieva.myplanktimer.data.Plank
import org.company.annamedvedieva.myplanktimer.databinding.ResultItemBinding

class PlanksListAdapter(val clickListener: PlankListener): ListAdapter<Plank, PlanksListAdapter.ViewHolder>(PlankDiffCallback()){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    class ViewHolder private constructor(val binding: ResultItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Plank, clickListener: PlankListener) {
            binding.plank = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ResultItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}


class PlankDiffCallback : DiffUtil.ItemCallback<Plank>() {

    override fun areItemsTheSame(oldItem: Plank, newItem: Plank): Boolean {
        return oldItem.plankId == newItem.plankId
    }

    override fun areContentsTheSame(oldItem: Plank, newItem: Plank): Boolean {
        return oldItem == newItem
    }
}

class PlankListener(val clickListener: (plankId: Long) -> Unit) {
    fun onClick(plank: Plank) = clickListener(plank.plankId)
}