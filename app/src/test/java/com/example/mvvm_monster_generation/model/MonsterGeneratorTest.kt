package com.example.mvvm_monster_generation.model

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MonsterGeneratorTest {

    private lateinit var monsterGenerator : MonsterGenerator

    @Before
    fun setup(){
        monsterGenerator = MonsterGenerator()
    }

    @Test
    fun TestGenerateHitPoints() {

        val attributes = MonsterAttributes(7,3,10)
        val name = "Rikachu"
        val expectedMonster = Monster(name,84,attributes.intelligence,attributes.strength,attributes.endurance)

        Assert.assertEquals(expectedMonster, monsterGenerator.generateMonster(attributes,name))
    }
}