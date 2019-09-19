package com.simpletech.simpleassets.helper

import org.json.JSONObject

/**
 * Created by antho.firuze@gmail.com on 5/09/2019.
 */

//val URL_API = "http://192.168.43.72:5050"
//val URL_API = "http://192.168.1.33:5050"
const val URL_API = "https://api.kenziotech.com"
const val AGENT = "android"
const val APPCODE = "dalwa.app"
const val LANG = "id"
val jRequest = JSONObject()

fun setRequest(method: String, params: Map<Any, Any> = emptyMap()): String {
    jRequest.put("method", method)
    if (params.isNotEmpty())
        jRequest.put("params", JSONObject(params))

    if (session!!.token.isNotEmpty())
        jRequest.put("token", session!!.token)

    return jRequest.toString()
}

class RestAPI