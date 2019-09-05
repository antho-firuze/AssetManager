package com.simpletech.simpleassets.models

import io.realm.RealmObject
import java.util.*

/**
 * Created by antho.firuze@gmail.com on 2018-07-26.
 */

open class History : RealmObject() {
    var id: String = UUID.randomUUID().toString()
    var item_id: String = ""
    var date: Date? = null
    var estimate_date: Date? = null
    var activity: String = ""
    var vendor: String = ""
    var description: String = ""
    var value: Double = 0.0
    var note1: String = ""
    var note2: String = ""
    var note3: String = ""
}