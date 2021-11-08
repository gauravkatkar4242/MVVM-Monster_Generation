package com.example.mvvm_monster_generation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_monster_generation.model.Monster
import com.example.mvvm_monster_generation.model.MonsterAttributes
import com.example.mvvm_monster_generation.model.MonsterGenerator
import java.text.FieldPosition

class AddMonsterViewModel(private val generator: MonsterGenerator = MonsterGenerator()): ViewModel() {

    private val monsterLiveData = MutableLiveData<Monster>()

    fun getMonsterLiveData(): LiveData<Monster> = monsterLiveData

    var name: String = ""
    var hitpoints: Int = 0
    var drawable: Int = 0
    var intelligence: Int = 0
    var strength: Int = 0
    var endurance: Int = 0

    lateinit var monster: Monster

    fun updateMonster(){
        val attributes = MonsterAttributes(intelligence, strength, endurance)
        monster = generator.generateMonster(attributes,name,drawable)
        monsterLiveData.postValue(monster)
        Log.d("ViewModel", monster.hitpoints.toString())
    }

    fun onAttributedSelected(attribute: Int, position: Int){
        when (attribute){
            1 ->
                this.intelligence = (position) * 3
            2 ->
                this.endurance = (position) * 3
            3 ->
                this.strength = (position) * 3
        }
        updateMonster()
    }
}