package com.firuze.ahmad.assetmanager.models

import io.realm.RealmObject
import java.util.*

/**
 * Created by antho.firuze@gmail.com on 2018-07-26.
 */

open class Items : RealmObject() {
    var id: String = UUID.randomUUID().toString()
    var name: String = ""
    var category: String = ""
    var brand_name: String = ""
    var model: String = ""
    var serial_number: String = ""
    var manufacture_date: Date? = null
    var purchase_date: Date? = null
    var value: Double = 0.0
    var location: String = ""
    var description: String = ""
    var picture1: String = ""
    var picture2: String = ""
    var picture3: String = ""
    var picture4: String = ""
    var picture5: String = ""
}