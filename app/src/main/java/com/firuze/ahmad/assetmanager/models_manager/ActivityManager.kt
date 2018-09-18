package com.firuze.ahmad.assetmanager.models_manager

import com.firuze.ahmad.assetmanager.models.Activity
import io.realm.Realm
import io.realm.kotlin.where
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

/**
 * Created by antho.firuze@gmail.com on 2018-07-26.
 */
 
class ActivityManager {
    val realm: Realm = Realm.getDefaultInstance()

    fun find(): Activity? {
        return realm.where<Activity>().findFirst()
    }

    fun findById(id: Long): Activity? {
        return realm.where<Activity>().equalTo("id", id).findFirst()
    }

    fun findAll(): List<Activity> {
        return realm.where<Activity>().findAll()
    }

    fun insertFromJson(j: JSONObject) {
        realm.executeTransaction { realm -> realm.createObjectFromJson(Activity::class.java, j) }
    }

    fun insertFromJsonList(j: JSONArray) {
        try {
            realm.executeTransaction { realm -> realm.createAllFromJson(Activity::class.java, j) }
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
        val results = realm.where(Activity::class.java!!).equalTo("id", id).findAll()
        results.deleteAllFromRealm()
        realm.commitTransaction()
    }


}