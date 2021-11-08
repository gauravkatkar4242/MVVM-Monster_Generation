package com.example.mvvm_monster_generation.model

import androidx.lifecycle.LiveData

interface MonsterRepository {

    fun saveMontser(monster : Monster)
    fun getAllMonsters(): LiveData<List<Monster>>
    fun clearAllMonsters()
}