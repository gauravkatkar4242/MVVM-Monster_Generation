package com.example.mvvm_monster_generation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_monster_generation.model.Monster
import com.example.mvvm_monster_generation.utils.MonsterDAO
import io.realm.RealmResults

class MainActivityViewModel: ViewModel() {

    private val MonstersLiveData = MutableLiveData<MutableList<Monster>>()

    fun getMonsterLiveData(): LiveData<MutableList<Monster>> = MonstersLiveData

    fun getMonsters(){
        val data : RealmResults<Monster>
        val monsterdao : MonsterDAO = MonsterDAO()
        data = monsterdao.getAllMonsters()
        MonstersLiveData.postValue(data)
    }
}