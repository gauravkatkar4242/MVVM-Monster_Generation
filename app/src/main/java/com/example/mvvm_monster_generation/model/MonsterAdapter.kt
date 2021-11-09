package com.example.mvvm_monster_generation.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_monster_generation.R
import com.example.mvvm_monster_generation.databinding.ActivityMainBinding
import org.w3c.dom.Text

class MonsterAdapter(private val context: Context, private val dataset: List<Monster>?):
    RecyclerView.Adapter<MonsterAdapter.MonsterViewHolder>()  {

    class MonsterViewHolder(private val view: View): RecyclerView.ViewHolder(view){

        var name = view.findViewById<TextView>(R.id.name)
        var hitpoints = view.findViewById<TextView>(R.id.hitpoints)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonsterViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.monster, parent, false)

        return MonsterViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: MonsterViewHolder, position: Int) {

        val item = dataset?.get(position)
        if (item != null) {
            holder.name.setText(item.name)
            holder.hitpoints.setText(item.hitpoints.toString())
        }
    }

    override fun getItemCount(): Int = dataset!!.size

}