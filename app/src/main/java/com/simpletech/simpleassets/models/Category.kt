package com.simpletech.simpleassets.models

import io.realm.RealmObject
import java.util.*

/**
 * Created by antho.firuze@gmail.com on 2018-07-26.
 */

open class Category : RealmObject() {
    var id: String = UUID.randomUUID().toString()
    var name: String = ""
}