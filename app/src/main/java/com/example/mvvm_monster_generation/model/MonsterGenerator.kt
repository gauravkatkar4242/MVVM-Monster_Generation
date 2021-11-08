package com.example.mvvm_monster_generation.model

class MonsterGenerator {
    fun generateMonster(attributes: MonsterAttributes, name: String, drawable: Int = 0): Monster {

        val hitpoins = 5 * attributes.intelligence +
                4 * attributes.endurance +
                3 * attributes.strength

        return Monster(name,hitpoins,drawable,attributes.intelligence,attributes.strength,attributes.endurance)
    }
}