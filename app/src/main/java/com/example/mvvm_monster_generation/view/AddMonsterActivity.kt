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
import com.example.mvvm_monster_generation.utils.PerformanceParametersSealedClass
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

        supportActionBar?.setDisplayHomeAsUpEnabled(true) // for back button

        configSpinnerAdaptors()
        clickListeners()
        observers()
    }

    private fun clickListeners(){
        binding.save.setOnClickListener {
            if (viewModel.insertInDB(binding.ipName.text.toString())){
                Toast.makeText(this, "Successful",Toast.LENGTH_LONG)
            }
            else{
                Toast.makeText(this, "Something went wrong",Toast.LENGTH_LONG)
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun observers(){

        viewModel.getMonsterLiveData().observe(this, Observer { monster ->
            binding.hitpoints.text = monster.hitpoints.toString()
        })

    }
    private fun configSpinnerAdaptors() {

        val intelligence_options = arrayOf("No Selection", "Ant Man", "Black Widow", "Tony Stark")
        val endurance_options = arrayOf("No Selection", "Thor", "Hulk", "Captain Marvel")
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
                    Log.d("AddMonster Activity", "On item selected intelligence_spinner $p2")
//                    viewModel.onAttributedSelected(PerformanceParameters.INTELLIGENCE, p2)
                    viewModel.onAttributedSelected(PerformanceParametersSealedClass.intelligence(),p2)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    Log.d("AddMonster Activity", "On nothing selected intelligence_spinner")
                }
            }

        binding.ipEndurance.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Log.d("AddMonster Activity", "On item selected endurance_spinner $p2")
//                viewModel.onAttributedSelected(PerformanceParameters.ENDURANCE, p2)
//                hitpoints.setText(viewModel.getMonsterLiveData().value?.hitpoints.toString())
                viewModel.onAttributedSelected(PerformanceParametersSealedClass.endurance(),p2)

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.d("AddMonster Activity", "On nothing selected endurance_spinner")
            }
        }
        binding.ipStrength.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Log.d("AddMonster Activity", "On item selected strength_spinner $p2")
//                viewModel.onAttributedSelected(PerformanceParameters.STRENGTH, p2)
//                hitpoints.setText(viewModel.getMonsterLiveData().value?.hitpoints.toString())
                viewModel.onAttributedSelected(PerformanceParametersSealedClass.strength(),p2)

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.d("AddMonster Activity", "On nothing selected strength_spinner")
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}