package com.example.mvvm_monster_generation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_monster_generation.R
import com.example.mvvm_monster_generation.viewModel.AddMonsterViewModel

class AddMonster : AppCompatActivity() {


    lateinit var intelligence_spinner: Spinner
    lateinit var stregth_spinner: Spinner
    lateinit var endurance_spinner: Spinner
    lateinit var hitpoints: TextView

    private lateinit var viewModel : AddMonsterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_monster)

        intelligence_spinner = findViewById<Spinner>(R.id.ip_intelligence)
        stregth_spinner = findViewById<Spinner>(R.id.ip_strength)
        endurance_spinner = findViewById<Spinner>(R.id.ip_endurance)
        hitpoints = findViewById<TextView>(R.id.hitpoints)


        viewModel = ViewModelProvider(this)[AddMonsterViewModel::class.java]

        configSpinerAdaptors()

    }

    private fun configSpinerAdaptors() {
        val intelligence_options = arrayOf("No Selection", "Ant Man","Black Widow","Tony Stark")
        val endurance_options = arrayOf("No Selection","Thor","Hulk","Captain Marval")
        val strength_options = arrayOf("No Selection", "Spider Man","Captain America","Iron Man")

        intelligence_spinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,intelligence_options)
        endurance_spinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,endurance_options)
        stregth_spinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strength_options)

        intelligence_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Log.d("AddMontser Acticity", "On item selected intelligence_spinner $p2")
                viewModel.onAttributedSelected(1,p2)
                hitpoints.setText(viewModel.getMonsterLiveData().value?.hitpoints.toString())
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.d("AddMontser Acticity", "On nothing selected intelligence_spinner")
            }

        }

        endurance_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Log.d("AddMontser Acticity", "On item selected endurance_spinner $p2")
                viewModel.onAttributedSelected(2,p2)
                hitpoints.setText(viewModel.getMonsterLiveData().value?.hitpoints.toString())


            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.d("AddMontser Acticity", "On nothing selected endurance_spinner")
            }

        }

        stregth_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Log.d("AddMontser Acticity", "On item selected stregth_spinner $p2")
                viewModel.onAttributedSelected(3,p2)
                hitpoints.setText(viewModel.getMonsterLiveData().value?.hitpoints.toString())

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.d("AddMontser Acticity", "On nothing selected stregth_spinner")
            }

        }
    }


}