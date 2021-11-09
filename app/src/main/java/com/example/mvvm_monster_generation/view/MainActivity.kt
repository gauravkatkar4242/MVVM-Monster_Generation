package com.example.mvvm_monster_generation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvm_monster_generation.R
import com.example.mvvm_monster_generation.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
               binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.addMonster.setOnClickListener {
            val intent = Intent(this, AddMonsterActivity::class.java)
            startActivity(intent)
        }
    }
}