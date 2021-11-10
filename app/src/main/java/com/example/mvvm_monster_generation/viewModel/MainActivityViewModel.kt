package com.example.mvvm_monster_generation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_monster_generation.model.Monster
import com.example.mvvm_monster_generation.DAO.MonsterDAOimplementation
import io.realm.RealmResults

class MainActivityViewModel() : ViewModel() {
    lateinit var data : RealmResults<Monster>
    var monsterdao : MonsterDAOimplementation = MonsterDAOimplementation()

    private val MonstersLiveData = MutableLiveData<MutableList<Monster>>()

    fun getMonsterLiveData(): LiveData<MutableList<Monster>> = MonstersLiveData

    fun getMonsters(): List<Monster> {
        data = monsterdao.getAllMonsters()
        MonstersLiveData.postValue(data)
        return data.toList()
    }
    fun deleteMonster(monster: Monster): Boolean {
        return (monsterdao.deleteMonster(monster))
    }
}