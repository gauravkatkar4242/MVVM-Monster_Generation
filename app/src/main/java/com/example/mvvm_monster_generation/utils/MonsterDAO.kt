package com.example.mvvm_monster_generation.utils

import com.example.mvvm_monster_generation.model.Monster
import io.realm.Realm
import io.realm.RealmResults
import java.lang.Exception

class MonsterDAO {

    private var realm = Realm.getDefaultInstance()

    fun getAllMonsters(): RealmResults<Monster> {
        return realm.where(Monster::class.java).findAll()
    }

    fun insertMonster(monster: Monster): Boolean{

        try{
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(monster)
            realm.commitTransaction()
            return true
        }
        catch (e: Exception){
            return false
        }
    }


}