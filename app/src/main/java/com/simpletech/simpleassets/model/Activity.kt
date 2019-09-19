package com.simpletech.simpleassets.model

import io.realm.RealmObject
import java.util.*

/**
 * Created by antho.firuze@gmail.com on 2018-07-26.
 */

open class Activity : RealmObject() {
    var id: String = UUID.randomUUID().toString()
    var name: String = ""   // service, washing, replace ink, replace filter, etc
}