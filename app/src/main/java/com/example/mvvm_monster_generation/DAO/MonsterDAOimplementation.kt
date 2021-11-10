package com.example.mvvm_monster_generation.DAO

import com.example.mvvm_monster_generation.model.Monster
import io.realm.Realm
import io.realm.RealmResults
import java.lang.Exception

class MonsterDAOimplementation(val realm: Realm = Realm.getDefaultInstance()) {


    fun getAllMonsters(): RealmResults<Monster> {
        return realm.where(Monster::class.java).findAll()
    }

    fun insertMonster(monster: Monster): Boolean {

        try {
            realm.executeTransaction { it.copyToRealmOrUpdate(monster) }
//            realm.beginTransaction()
//            realm.copyToRealmOrUpdate(monster)
//            realm.commitTransaction()
            return true
        } catch (e: Exception) {
            return false
        }
    }

    fun deleteMonster(monster: Monster): Boolean {

        try {
            realm.executeTransaction {
                it.where(Monster::class.java)
                    .equalTo("name", monster.name)
                    .findFirst()?.deleteFromRealm()
            }
            return true
        } catch (e: Exception) {
            return false
        }

    }


}