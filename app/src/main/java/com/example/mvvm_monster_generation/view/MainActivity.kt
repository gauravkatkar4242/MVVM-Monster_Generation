package com.example.mvvm_monster_generation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_monster_generation.R
import com.example.mvvm_monster_generation.databinding.ActivityMainBinding
import com.example.mvvm_monster_generation.model.Monster
import com.example.mvvm_monster_generation.model.MonsterAdapter
import com.example.mvvm_monster_generation.utils.MonsterDAO
import com.example.mvvm_monster_generation.viewModel.AddMonsterViewModel
import com.example.mvvm_monster_generation.viewModel.MainActivityViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.realm.RealmResults

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]


        viewModel.getMonsters()

        viewModel.getMonsterLiveData().observe(this, Observer { monsterLivedata ->
//            binding.opText.setText(monsterLivedata.toString())
            binding.rvMonsterList.adapter = MonsterAdapter (this, monsterLivedata )
            Log.d("MainActicity", monsterLivedata.toString())

        })
        binding.addMonster.setOnClickListener {
            val intent = Intent(this, AddMonsterActivity::class.java)
            startActivity(intent)
        }
    }
}