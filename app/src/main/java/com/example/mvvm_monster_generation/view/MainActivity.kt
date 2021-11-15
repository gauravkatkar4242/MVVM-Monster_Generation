package com.example.mvvm_monster_generation.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_monster_generation.databinding.ActivityMainBinding
import com.example.mvvm_monster_generation.model.Monster
import com.example.mvvm_monster_generation.model.MonsterAdapter
import com.example.mvvm_monster_generation.utils.MonsterAdapterClickListener
import com.example.mvvm_monster_generation.viewModel.MainActivityViewModel
import io.realm.Realm

class MainActivity : AppCompatActivity(), MonsterAdapterClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private val realm = Realm.getDefaultInstance()
    lateinit var adapter: MonsterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this)[MainActivityViewModel(realm)::class.java]

        adapter = MonsterAdapter(this, viewModel.getMonsters(), this)
        binding.rvMonsterList.adapter = adapter


        initObservers()

        Toast.makeText(this, "Successfully Deleted", Toast.LENGTH_SHORT)
        binding.addMonster.setOnClickListener {
            val intent = Intent(this, AddMonsterActivity::class.java)
            startActivity(intent)
        }

    }

    fun initObservers() {

//        viewModel.getMonsterLiveData().observe(this, { monsterLivedata ->
//            adapter.submitList(monsterLivedata.toMutableList())
//            Log.d("MainActicity", "initObserver $monsterLivedata.toString()")
//        })
        viewModel.getliveRealmResults().observe(this, Observer {
            Log.d("@@@$$##@@", it.toString())
            if (it != null) {
                adapter.submitList(it.toMutableList())
            }

        })
    }

    override fun onDeleteClicked(monster: Monster?) {
        viewModel.deleteMonster(monster!!)

    }


    override fun submitNewData() {

    }


}