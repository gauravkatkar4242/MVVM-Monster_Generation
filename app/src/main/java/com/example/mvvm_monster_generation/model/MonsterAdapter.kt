package com.example.mvvm_monster_generation.model

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_monster_generation.databinding.MonsterBinding
import com.example.mvvm_monster_generation.utils.MonsterAdapterClickListener
import io.realm.mongodb.User

class MonsterAdapter(
    private val context: Context,
    var dataset: MutableList<Monster>?,
    private val clickListener: MonsterAdapterClickListener
) :
    ListAdapter<Monster, MonsterAdapter.MonsterViewHolder>((UserItemDiffCallback())) {

//    inner class MonsterViewHolder(val binding: MonsterBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        init {
//            binding.delete.setOnClickListener {
//                if (dataset != null) {
//                    var currPosition = adapterPosition
//                    clickListener.onDeleteClicked(dataset!![currPosition])
////                    notifyItemRemoved(currPosition)
////                    notifyItemRangeChanged(currPosition, getItemCount());
//
//                }
//            }
//        }
//    }


    class MonsterViewHolder(val binding: MonsterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun ifDeletePressed(monster: Monster?, clickListener: MonsterAdapterClickListener) {
            binding.delete.setOnClickListener {
                clickListener.onDeleteClicked(monster)
//                Log.d("MonsterAdapter", dataset.toString())
            }
        }
    }

    class UserItemDiffCallback : DiffUtil.ItemCallback<Monster>() {
        override fun areItemsTheSame(oldItem: Monster, newItem: Monster): Boolean {
            Log.d("in areItemsSame", "$oldItem, $newItem")
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Monster, newItem: Monster): Boolean = oldItem == newItem
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

            holder.ifDeletePressed(monster, clickListener)
        }
    }

//    override fun getItemCount(): Int = dataset!!.size

    fun updateData(listMonster: MutableList<Monster>) {
//        this.dataset = listMonster

        this.dataset = listMonster
        notifyDataSetChanged()
    }

}