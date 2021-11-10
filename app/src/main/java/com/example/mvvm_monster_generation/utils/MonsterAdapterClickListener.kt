package com.example.mvvm_monster_generation.utils

import com.example.mvvm_monster_generation.model.Monster

interface MonsterAdapterClickListener {

    fun onDeleteClicked(monster : Monster?)

}