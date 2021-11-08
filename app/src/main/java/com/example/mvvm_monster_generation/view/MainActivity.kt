package com.example.mvvm_monster_generation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvm_monster_generation.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var add_monster = findViewById<FloatingActionButton>(R.id.add_monster)
        add_monster.setOnClickListener {
            val intent = Intent(this, AddMonsterActivity::class.java)
            startActivity(intent)
        }
    }
}