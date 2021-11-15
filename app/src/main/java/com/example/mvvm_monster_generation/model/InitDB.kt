package com.example.mvvm_monster_generation.model

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class InitDB : Application() {
    override fun onCreate() {
        super.onCreate()



        Realm.init(this)

        val config = RealmConfiguration.Builder()
            .name("Monster.db")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(1)
            .allowQueriesOnUiThread(true)
            .allowWritesOnUiThread(true)
            .build()

        Realm.setDefaultConfiguration(config)
    }
}