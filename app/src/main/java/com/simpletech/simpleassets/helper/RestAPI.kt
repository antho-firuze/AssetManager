package com.simpletech.dalwamobile.helper

import org.json.JSONObject

/**
 * Created by antho.firuze@gmail.com on 5/09/2019.
 */

//val URL_API = "http://192.168.43.72:5050"
//val URL_API = "http://192.168.1.33:5050"
val URL_API = "https://api.kenziotech.com"
val AGENT = "android"
val APPCODE = "dalwa.app"
val LANG = "id"
val jRequest = JSONObject()

fun setRequest(method: String, params: Map<Any, Any> = emptyMap()): String {
    jRequest.put("method", method)
    if (params.isNotEmpty())
        jRequest.put("params", JSONObject(params))

    if (session!!.token.isNotEmpty())
        jRequest.put("token", session!!.token)

    return jRequest.toString()
}

class RestAPI {

}