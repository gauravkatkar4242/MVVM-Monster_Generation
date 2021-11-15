package com.example.mvvm_monster_generation.DAO

import com.example.mvvm_monster_generation.model.Monster
import io.realm.Realm
import io.realm.RealmResults

class MonsterDAOimplementation(val realm: Realm = Realm.getDefaultInstance()) {


    fun getAllMonsters(): RealmResults<Monster> {
        return realm.where(Monster::class.java).findAll()
    }

//    fun getAllMonsters(): MutableList<Monster>? {
//        val results = realm.where(Monster::class.java).findAll()
//        return realm.copyFromRealm(results)
////        return results.toMutableList()
//    }

    fun insertMonster(monster: Monster): Boolean {

        try {
            realm.executeTransaction { it.copyToRealmOrUpdate(monster) }
            return true
        } catch (e: Exception) {
            return false
        }
    }

    fun deleteMonster(monster: Monster): Boolean {

        return try {
            realm.executeTransaction {
                it.where(Monster::class.java)
                    .equalTo("name", monster.name)
                    .findFirst()?.deleteFromRealm()
            }
            true
        } catch (e: Exception) {
            false
        }


    }


//            realm.executeTransactionAsync (
//                { realm ->
//                    realm.where(Monster::class.java)
//                        .equalTo("name", monster.name)
//                        .findFirst()?.deleteFromRealm()
//                }, //OnSuccess
//                {
//                    Log.d("DAOimplemeatation", "Success")
//                },
//                {
//                    Log.d("DAOimplemeatation", it.message.toString())
//
//                }
//            )


}