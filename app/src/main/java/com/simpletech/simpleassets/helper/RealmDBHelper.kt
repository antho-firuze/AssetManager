package com.simpletech.simpleassets.helper

import com.simpletech.simpleassets.model.Category
import com.simpletech.simpleassets.model.Items
import io.realm.Realm
import io.realm.RealmModel
import io.realm.annotations.RealmClass
import io.realm.kotlin.where

/**
 * Created by antho.firuze@gmail.com on 5/09/2019.
 */

var realm: Realm? = null

class RealmDBHelper {

    fun initDatabaseFirstLaunch() {
        deleteCategoryAll()
        deleteItemsAll()

        var rows = realm?.where<Category>()?.findAll()
        if (rows!!.isEmpty()) {
            insertCategory("Category1")
            insertCategory("Category2")
            insertCategory("Category3")
            insertCategory("Category4")
            insertCategory("Category5")
        }

        var rows1 = realm?.where<Items>()?.findAll()
        if (rows1!!.isEmpty()) {
            insertItem("Item1", "Item model 1")
            insertItem("Item2", "Item model 2")
            insertItem("Item3", "Item model 3")
            insertItem("Item4", "Item model 4")
            insertItem("Item5", "Item model 5")
        }
    }

    fun insertCategory(s: String) {
        realm?.executeTransaction {
            val obj = realm!!.createObject(Category::class.java)
            obj.name = s
        }
    }

    fun insertItem(s: String, s1: String) {
        realm?.executeTransaction {
            val obj = realm!!.createObject(Items::class.java)
            obj.name = s
            obj.model = s1
        }
    }

    fun deleteCategoryAll() {
        realm?.executeTransaction {
            realm!!.where(Category::class.java).findAll().deleteAllFromRealm()
        }
    }

    fun deleteItemsAll() {
        realm?.executeTransaction {
            realm!!.where(Items::class.java).findAll().deleteAllFromRealm()
        }
    }
}


//realm?.executeTransaction {
//    realm!!.where(Items::class.java).findAll().deleteAllFromRealm()
//}
