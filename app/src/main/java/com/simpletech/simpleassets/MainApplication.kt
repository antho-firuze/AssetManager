package com.simpletech.simpleassets

import android.app.Application
import android.util.Log
import com.simpletech.simpleassets.helper.Session
import com.simpletech.simpleassets.helper.realm
import com.simpletech.simpleassets.helper.session
import com.simpletech.simpleassets.model.Items
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.where

/**
 * Created by antho.firuze@gmail.com on 9/09/2019.
 */

class MainApplication : Application() {

    val TAG = "MainApplication"

    override fun onCreate() {
        super.onCreate()

        session = Session(applicationContext)

        // ... Initialization Realm Database
        Realm.init(this)
        val config =
            RealmConfiguration.Builder().name("simpleAssets.realm").schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(config)
        realm = Realm.getDefaultInstance()
    }
}