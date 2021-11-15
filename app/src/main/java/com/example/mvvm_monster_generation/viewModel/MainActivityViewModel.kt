package com.example.mvvm_monster_generation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_monster_generation.model.Monster
import com.example.mvvm_monster_generation.DAO.MonsterDAOimplementation
import com.example.mvvm_monster_generation.model.LiveRealmResults
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmResults

class MainActivityViewModel(val realm: Realm = Realm.getDefaultInstance()) : ViewModel() {
    private lateinit var data : RealmResults<Monster>
    var monsterdao : MonsterDAOimplementation = MonsterDAOimplementation(realm)


    private lateinit var liveRealmResults: LiveRealmResults<Monster>
    fun getliveRealmResults(): LiveRealmResults<Monster> = liveRealmResults

//    private val monstersLiveData = MutableLiveData<MutableList<Monster>>()
//    fun getMonsterLiveData(): LiveData<MutableList<Monster>> {
//        return monstersLiveData
//    }

    fun getMonsters(): MutableList<Monster> {
        data = monsterdao.getAllMonsters()!!
        liveRealmResults = LiveRealmResults(data)

//        monstersLiveData.postValue(data)
//        data.addChangeListener(RealmChangeListener {
//            it.forEach { monster ->
//                Log.d("@@@@@@@@@@@@@@@@@@@@@", monster.toString())
//            }
//            monstersLiveData.postValue(it.toMutableList())
//        })

        return data.toMutableList()
    }
    fun deleteMonster(monster: Monster) {
        if (monsterdao.deleteMonster(monster))
        {
//            getMonsters()
        }
    }


    override fun onCleared() {
        super.onCleared()
    }
}