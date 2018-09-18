package com.firuze.ahmad.assetmanager.models_manager

import com.firuze.ahmad.assetmanager.models.Category
import io.realm.Realm
import io.realm.kotlin.where
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

/**
 * Created by antho.firuze@gmail.com on 2018-07-26.
 */
 
class CategoryManager {
    val realm: Realm = Realm.getDefaultInstance()

    fun find(): Category? {
        return realm.where<Category>().findFirst()
    }

    fun findById(id: Long): Category? {
        return realm.where<Category>().equalTo("id", id).findFirst()
    }

    fun findAll(): List<Category> {
        return realm.where<Category>().findAll()
    }

    fun insertFromJson(j: JSONObject) {
        realm.executeTransaction { realm -> realm.createObjectFromJson(Category::class.java, j) }
    }

    fun insertFromJsonList(j: JSONArray) {
        try {
            realm.executeTransaction { realm -> realm.createAllFromJson(Category::class.java, j) }
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
        val results = realm.where(Category::class.java!!).equalTo("id", id).findAll()
        results.deleteAllFromRealm()
        realm.commitTransaction()
    }


}