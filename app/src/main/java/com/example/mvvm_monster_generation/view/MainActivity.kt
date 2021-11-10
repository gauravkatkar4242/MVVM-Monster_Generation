package com.example.mvvm_monster_generation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_monster_generation.databinding.ActivityMainBinding
import com.example.mvvm_monster_generation.model.Monster
import com.example.mvvm_monster_generation.model.MonsterAdapter
import com.example.mvvm_monster_generation.utils.MonsterAdapterClickListener
import com.example.mvvm_monster_generation.viewModel.MainActivityViewModel

class MainActivity : AppCompatActivity(), MonsterAdapterClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    lateinit var adapter : MonsterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        adapter =  MonsterAdapter (this,viewModel.getMonsters(),this)
        binding.rvMonsterList.adapter = adapter


        initObservers()

        Toast.makeText(this,"Successfully Deleted", Toast.LENGTH_SHORT)
        binding.addMonster.setOnClickListener {
            val intent = Intent(this, AddMonsterActivity::class.java)
            startActivity(intent)
        }

    }

    fun initObservers(){

        viewModel.getMonsterLiveData().observe(this, Observer { monsterLivedata ->
            binding.rvMonsterList.adapter?.notifyDataSetChanged()
            Log.d("MainActicity", monsterLivedata.toString())

        })

    }

    override fun onDeleteClicked(monster: Monster?) {

        if (viewModel.deleteMonster(monster!!)){
            binding.rvMonsterList.adapter?.notifyDataSetChanged()
            adapter.updateData(viewModel.getMonsters())
        }
        else{
            Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
        }

    }
}