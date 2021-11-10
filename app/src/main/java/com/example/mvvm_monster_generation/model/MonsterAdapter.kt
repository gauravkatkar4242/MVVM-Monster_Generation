package com.example.mvvm_monster_generation.model

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_monster_generation.databinding.MonsterBinding
import com.example.mvvm_monster_generation.utils.MonsterAdapterClickListener

class MonsterAdapter(
    private val context: Context,
    private var dataset: List<Monster>?,
    val clickListener: MonsterAdapterClickListener
) :
    RecyclerView.Adapter<MonsterAdapter.MonsterViewHolder>() {

    inner class MonsterViewHolder(val binding: MonsterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.delete.setOnClickListener {
                if (dataset != null) {
                    var currPosition = adapterPosition
                    clickListener.onDeleteClicked(dataset!![currPosition])
//                    notifyItemRemoved(currPosition)
//                    notifyItemRangeChanged(currPosition, getItemCount());

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonsterViewHolder {
        val adapterLayout = MonsterBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MonsterViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: MonsterViewHolder, position: Int) {

        dataset?.get(position).let { monster ->
            holder.binding.name.text = monster?.name
            holder.binding.hitpoints.text = monster?.hitpoints.toString()

        }
    }

    override fun getItemCount(): Int = dataset!!.size

    fun updateData(listMOnster: List<Monster>) {
        this.dataset = listMOnster
        notifyDataSetChanged()

    }

}