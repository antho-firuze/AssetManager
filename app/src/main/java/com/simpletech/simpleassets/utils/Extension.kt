package com.simpletech.simpleassets.utils

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import com.simpletech.simpleassets.activity.item.AddItemActivity
import java.text.SimpleDateFormat
import java.util.*
import androidx.core.content.ContextCompat.startActivity
import android.Manifest.permission
import androidx.core.app.ActivityCompat
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.content.ContextCompat



/**
 * Created by antho.firuze@gmail.com on 4/10/2019.
 */

private var toast: Toast? = null

internal fun Activity.toast(message: CharSequence) {
    toast?.cancel()
    toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        .apply { show() }
}

fun Activity.launchActivity(clss: Class<*>) {
    val intent = Intent(this, clss)
    startActivity(intent)
}

fun Activity.rateApp() {
    try {
        var playstoreuri1: Uri = Uri.parse("market://details?id=$packageName")
        //or you can add
        var playstoreIntent1: Intent = Intent(Intent.ACTION_VIEW, playstoreuri1)
        startActivity(playstoreIntent1)
        //it genrate exception when devices do not have playstore
    }catch (exp:Exception){
        var playstoreuri2: Uri = Uri.parse("http://play.google.com/store/apps/details?id=$packageName")
        var playstoreIntent2: Intent = Intent(Intent.ACTION_VIEW, playstoreuri2)
        startActivity(playstoreIntent2)
    }
}

fun Activity.sharedApp() {
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_TEXT, "http://play.google.com/store/apps/details?id=$packageName")
    intent.putExtra(Intent.EXTRA_SUBJECT, "Simple Asset Apps")
    startActivity(Intent.createChooser(intent, "Share via"))
}

internal fun Int.toHex() = "#${Integer.toHexString(this)}"

internal fun Calendar.formatTime(): String {
    return SimpleDateFormat("kk:mm a", Locale.US).format(this.time)
}

internal fun Calendar.formatDate(): String {
    return SimpleDateFormat("MMMM dd, yyyy", Locale.US).format(this.time)
}

internal fun Calendar.formatDateTime(): String {
    return SimpleDateFormat("kk:mm a, MMMM dd, yyyy", Locale.US).format(this.time)
}
