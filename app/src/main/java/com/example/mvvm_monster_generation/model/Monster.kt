package com.example.mvvm_monster_generation.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

//data class Monster(
//    val attributes: MonsterAttributes = MonsterAttributes(),
//    val name: String = "",
//    val hitpoints: Int = 0,
//    val drawable: Int = 0
//)

open class Monster(

    @PrimaryKey
    var name: String = "",
    var hitpoints: Int = 0,
    var drawable: Int = 0,
    var intelligence: Int = 0,
    var strength: Int = 0,
    var endurance: Int = 0
): RealmObject()