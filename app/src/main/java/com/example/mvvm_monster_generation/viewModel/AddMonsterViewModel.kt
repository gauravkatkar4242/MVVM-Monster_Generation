package com.example.mvvm_monster_generation.viewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_monster_generation.model.Monster
import com.example.mvvm_monster_generation.model.MonsterAttributes
import com.example.mvvm_monster_generation.model.MonsterGenerator
import com.example.mvvm_monster_generation.utils.PerformanceParameters
import com.example.mvvm_monster_generation.utils.PerformanceParametersSealedClass
import io.realm.Realm
import java.lang.Exception
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
    var hit1 : MutableLiveData<Int> = MutableLiveData()
    lateinit var monster: Monster

    fun updateMonster(){
        val attributes = MonsterAttributes(intelligence, strength, endurance)
        monster = generator.generateMonster(attributes,name,drawable)
        monsterLiveData.postValue(monster)
        Log.d("ViewModel", monster.hitpoints.toString())
    }

    fun updateHitpoint( hitPoint: Int) {
        hit1.value = hitPoint
    }

//    fun onAttributedSelected(attribute: PerformanceParameters, position: Int){
//        when(attribute) {
//            PerformanceParameters.INTELLIGENCE -> this.intelligence = (position) * 3
//            PerformanceParameters.ENDURANCE -> this.endurance = (position) * 3
//            PerformanceParameters.STRENGTH -> this.strength = (position) * 3
//        }
//
//        updateMonster()
//    }


    fun onAttributedSelected(attribute: PerformanceParametersSealedClass, position: Int){
        when(attribute) {
            is PerformanceParametersSealedClass.intelligence -> intelligence = (position) * 3
            is PerformanceParametersSealedClass.endurance -> endurance = (position) * 3
            is PerformanceParametersSealedClass.strength -> strength = (position) * 3
        }

        updateMonster()
    }

    fun insertInDB() {

    }
}
