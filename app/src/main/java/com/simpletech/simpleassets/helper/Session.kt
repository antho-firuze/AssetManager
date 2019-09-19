package com.simpletech.simpleassets.helper

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color

/**
 * Created by antho.firuze@gmail.com on 17/07/2019.
 */

var session: Session? = null

class Session(ctx: Context) {

    private val prefName = "MAIN_PREF"
    private val prefFirstLaunch = "_FIRST_LAUNCH"
    private val prefBgColor = "_BG_COLOR"
    private val prefIsLogin = "_IS_LOGIN"
    private val prefToken = "_TOKEN"
    private val prefLoginId = "_LOGIN_ID"
    private val prefUserName = "_USERNAME"
    private val prefClickOffer = "_CLICK_OFFER"
    private val MAX_CLICK_OFFER = 10
    private val sPref: SharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE)


    var isFirstLaunch
        get() = sPref.getBoolean(prefFirstLaunch, true)
        set(value) = sPref.edit().putBoolean(prefFirstLaunch, value).apply()

    var bgColor
        get() = sPref.getInt(prefBgColor, Color.BLACK)
        set(value) = sPref.edit().putInt(prefBgColor, value).apply()

    var isLogin
        get() = sPref.getBoolean(prefIsLogin, false)
        set(value) = sPref.edit().putBoolean(prefIsLogin, value).apply()

    var token
        get() = sPref.getString(prefToken, "")
        set(value) = sPref.edit().putString(prefToken, value).apply()

    var loginId
        get() = sPref.getInt(prefLoginId, 0)
        set(value) = sPref.edit().putInt(prefLoginId, value).apply()

    var userName
        get() = sPref.getString(prefUserName, "")
        set(value) = sPref.edit().putString(prefUserName, value).apply()

    fun actionClickOffer(): Boolean {
        var current = sPref.getInt(prefClickOffer, 1)
        var is_reset = false
        if (current < MAX_CLICK_OFFER) {
            current++
        } else {
            current = 1
            is_reset = true
        }
        sPref.edit().putInt(prefClickOffer, current).apply()
        return is_reset
    }

}