package com.simpletech.dalwamobile.helper

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by antho.firuze@gmail.com on 5/09/2019.
 */

const val ANSI_DATE_FORMAT = "yyyy-MM-dd"
const val EUROPE_DATE_FORMAT = "dd.MM.yyyy"
const val IDN_DATE_FORMAT = "dd-MM-yyyy"
const val FORM_DATE_FORMAT = "dd-MMM-yyyy"

const val ANSI_0_DATETIME_FORMAT = "yyyy-MM-dd HH:mm"  // 2018-01-15 21:35
const val ANSI_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss"  // 2018-01-15 21:35:00
const val ANSI_DATETIME_FORMAT_WITH_MS = "yyyy-MM-dd HH:mm:ss.SSS"  // 2015-01-01 00:00:00.000
const val IDN_DATETIME_FORMAT = "dd-MM-yyyy HH:mm:ss"
const val FORM_0_DATETIME_FORMAT = "dd-MMM-yyyy HH:mm"
const val FORM_DATETIME_FORMAT = "dd-MMM-yyyy HH:mm:ss"
const val JAVA_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"

//val DateFmt = SimpleDateFormat(IDN_DATE_FORMAT)
//val DateTimeFmt = SimpleDateFormat(IDN_DATETIME_FORMAT)
//val FormDateFmt = SimpleDateFormat(FORM_DATE_FORMAT)
//val FormDateTimeFmt = SimpleDateFormat(FORM_DATETIME_FORMAT)
//var JavaDateTimeFmt = SimpleDateFormat(JAVA_DATETIME_FORMAT)
//var AnsiDateTimeFmt = SimpleDateFormat(ANSI_DATETIME_FORMAT)

//val NAVTimeStamp = SimpleDateFormat("yyyy-MM-dd 11:00:00").format(Date())
val DateTimeStamp = SimpleDateFormat("yyyy-MM-dd 11:00:00").format(Date())

class DateTime