package com.firuze.ahmad.assetmanager.models_manager

import com.firuze.ahmad.assetmanager.models.Items
import io.realm.Realm
import io.realm.kotlin.where
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

/**
 * Created by antho.firuze@gmail.com on 2018-07-26.
 */
 
class ItemsManager {
    val realm: Realm = Realm.getDefaultInstance()

    fun find(): Items? {
        return realm.where<Items>().findFirst()
    }

    fun findById(id: Long): Items? {
        return realm.where<Items>().equalTo("id", id).findFirst()
    }

    fun findAll(): List<Items> {
        return realm.where<Items>().findAll()
    }

    fun insertFromJson(j: JSONObject) {
        realm.executeTransaction { realm -> realm.createObjectFromJson(Items::class.java, j) }
    }

    fun insertFromJsonList(j: JSONArray) {
        try {
            realm.executeTransaction { realm -> realm.createAllFromJson(Items::class.java, j) }
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
        val results = realm.where(Items::class.java!!).equalTo("id", id).findAll()
        results.deleteAllFromRealm()
        realm.commitTransaction()
    }


}