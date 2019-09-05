package com.simpletech.simpleassets.models_manager

import com.simpletech.simpleassets.models.History
import io.realm.Realm
import io.realm.kotlin.where
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

/**
 * Created by antho.firuze@gmail.com on 2018-07-26.
 */
 
class HistoryManager {
    val realm: Realm = Realm.getDefaultInstance()

    fun find(): History? {
        return realm.where<History>().findFirst()
    }

    fun findById(id: Long): History? {
        return realm.where<History>().equalTo("id", id).findFirst()
    }

    fun findAll(): List<History> {
        return realm.where<History>().findAll()
    }

    fun insertFromJson(j: JSONObject) {
        realm.executeTransaction { realm -> realm.createObjectFromJson(History::class.java, j) }
    }

    fun insertFromJsonList(j: JSONArray) {
        try {
            realm.executeTransaction { realm -> realm.createAllFromJson(History::class.java, j) }
        } catch (e: IOException) {
            if (realm.isInTransaction) {
                realm.cancelTransaction()
            }
            throw RuntimeException(e)
        } finally {
            realm.close()
        }
    }

    fun deleteById(id: Long) {
        realm.beginTransaction()
        val results = realm.where(History::class.java!!).equalTo("id", id).findAll()
        results.deleteAllFromRealm()
        realm.commitTransaction()
    }


}