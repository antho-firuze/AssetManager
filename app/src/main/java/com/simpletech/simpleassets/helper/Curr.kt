package com.simpletech.simpleassets.helper

import java.text.NumberFormat
import java.util.*

/**
 * Created by antho.firuze@gmail.com on 5/09/2019.
 */

/**
 * Currency & Percent Format
 */
//const val ANSI_CURRENCY_FORMAT = "#,##0.00;-#,##0.00"
//const val ANSI_PERCENT_FORMAT = "#,##0.00 %;-#,##0.00 %"
//val currFmtEN = DecimalFormat(ANSI_CURRENCY_FORMAT)
//val percentFmt = DecimalFormat(ANSI_PERCENT_FORMAT)

val localeID = Locale("in", "ID")
val currFmtID = NumberFormat.getCurrencyInstance(localeID)!!


class Curr