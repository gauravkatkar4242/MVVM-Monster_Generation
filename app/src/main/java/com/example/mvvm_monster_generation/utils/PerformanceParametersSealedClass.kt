package com.example.mvvm_monster_generation.utils

sealed class PerformanceParametersSealedClass {

    class intelligence(): PerformanceParametersSealedClass()
    class endurance(): PerformanceParametersSealedClass()
    class strength(): PerformanceParametersSealedClass()

}