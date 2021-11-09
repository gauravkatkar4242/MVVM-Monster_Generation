package com.example.mvvm_monster_generation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_monster_generation.R
import com.example.mvvm_monster_generation.databinding.ActivityAddMonsterBinding
import com.example.mvvm_monster_generation.databinding.ActivityMainBinding
import com.example.mvvm_monster_generation.utils.PerformanceParameters
import com.example.mvvm_monster_generation.viewModel.AddMonsterViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AddMonsterActivity : AppCompatActivity() {

    private lateinit var viewModel: AddMonsterViewModel
    private lateinit var binding: ActivityAddMonsterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddMonsterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[AddMonsterViewModel::class.java]

        configSpinerAdaptors()

        viewModel.getMonsterLiveData().observe(this, Observer { monster ->
            binding.hitpoints.setText(monster.hitpoints.toString())

        })

        var save_monster = findViewById<Button>(R.id.save)
        save_monster.setOnClickListener {
            viewModel.insertInDB()
        }

    }

    private fun configSpinerAdaptors() {

        val intelligence_options = arrayOf("No Selection", "Ant Man", "Black Widow", "Tony Stark")
        val endurance_options = arrayOf("No Selection", "Thor", "Hulk", "Captain Marval")
        val strength_options = arrayOf("No Selection", "Spider Man", "Captain America", "Iron Man")

        binding.ipIntelligence.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, intelligence_options)
        binding.ipEndurance.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, endurance_options)
        binding.ipStrength.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strength_options)

        binding.ipIntelligence.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    Log.d("AddMontser Acticity", "On item selected intelligence_spinner $p2")
                    viewModel.onAttributedSelected(PerformanceParameters.INTELLIGENCE, p2)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    Log.d("AddMontser Acticity", "On nothing selected intelligence_spinner")
                }
            }

        binding.ipEndurance.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Log.d("AddMontser Acticity", "On item selected endurance_spinner $p2")
                viewModel.onAttributedSelected(PerformanceParameters.ENDURANCE, p2)
//                hitpoints.setText(viewModel.getMonsterLiveData().value?.hitpoints.toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.d("AddMontser Acticity", "On nothing selected endurance_spinner")
            }
        }
        binding.ipStrength.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Log.d("AddMontser Acticity", "On item selected stregth_spinner $p2")
                viewModel.onAttributedSelected(PerformanceParameters.STRENGTH, p2)
//                hitpoints.setText(viewModel.getMonsterLiveData().value?.hitpoints.toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.d("AddMontser Acticity", "On nothing selected stregth_spinner")
            }
        }
    }
}