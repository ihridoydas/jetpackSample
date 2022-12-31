package com.ihridoydas.simpleapp.util.extensions

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

val ServerDateFormat = "yyyy-MM-dd"

/**
 * Date format : "yyyy-MM-dd HH:mm:ss"
 */
val ServerDateTimeFormat = "yyyy-MM-dd HH:mm:ss"

val Date.hour: Int
    get() = Calendar.getInstance().apply { time = this@hour }.get(Calendar.HOUR_OF_DAY)

val Date.min: Int
    get() = Calendar.getInstance().apply { time = this@min }.get(Calendar.MINUTE)

/**
 * Pattern: yyyy-MM-dd HH:mm:ss
 */
fun Date.formatToServerDateTimeDefaults(): String{
    val sdf= SimpleDateFormat(ServerDateTimeFormat, Locale.getDefault())
    return sdf.format(this)
}

fun Date.formatToTruncatedDateTime(): String{
    val sdf= SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault())
    return sdf.format(this)
}

/***
 * Default date time format yyyy年MM月dd日　EEEE
 */
fun Date.simpleDateTimeFormatStringForDisplay() : String {
    return "yyyy年M月d日　EEEE"
}

/**
 *  Pattern: yyyy年MM月dd日　EEEE
 */
fun Date.formatToLongDateForDisplay(): String{
    return SimpleDateFormat(simpleDateTimeFormatStringForDisplay(), Locale.getDefault()).format(this)
}

/**
 *  Pattern: yyyy年MM月dd日 (EEE)
 */
fun Date.formatToDateForDisplay(): String{
    return SimpleDateFormat( "yyyy年MM月dd日 (EEE)", Locale.JAPANESE).format(this)
}

/**
 *  Pattern: yyyy/MM/dd(EEE)
 */
fun Date.formatToDateWithDayForDisplay(): String{
    return SimpleDateFormat( "yyyy/MM/dd(EEE)", Locale.JAPANESE).format(this)
}

/**
 * Pattern: yyyy-MM-dd
 */
fun Date.formatToServerDateDefaults(): String{
    return SimpleDateFormat(ServerDateFormat, Locale.getDefault()).format(this)
}

/**
 * Pattern: HH:mm:ss
 */
fun Date.formatToServerTimeDefaults(): String{
    val sdf= SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return sdf.format(this)
}

/**
 * Pattern: dd/MM/yyyy HH:mm:ss
 */
fun Date.formatToViewDateTimeDefaults(): String{
    val sdf= SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    return sdf.format(this)
}
/**
 * Pattern: yyyy/MM/dd HH:mm
 */
fun Date.formatToViewDateTime(): String{
    val sdf= SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault())
    return sdf.format(this)
}

/**
 * Pattern: dd/MM/yyyy
 */
fun Date.formatToViewDateDefaults(): String{
    val sdf= SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return sdf.format(this)
}

/**
 * Pattern: HH:mm:ss
 */
fun Date.formatToViewTimeDefaults(): String{
    val sdf= SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return sdf.format(this)
}

/**
 * Pattern: HH:mm
 */
fun Date.formatToViewTimeDefaultExceptSec(): String{
    val sdf= SimpleDateFormat("HH:mm", Locale.getDefault())
    return sdf.format(this)
}

/**
 * Add field date to current date
 */
fun Date.add(field: Int, amount: Int): Date {
    Calendar.getInstance().apply {
        time = this@add
        add(field, amount)
        return time
    }
}
/**
 * String to Date (Instant)
 * Pattern: yyyy-MM-dd'T'HH:mm:ss+09:00
 */
fun stringToDate(value: String): Date {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss+09:00")
    val localDate = LocalDate.parse(value, formatter)
    return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
}
/**
 * String to Date
 * Pattern: yyyy-MM-dd'T'HH:mm:ss+09:00
 */
@SuppressLint("SimpleDateFormat")
fun stringFormatToDateFormat(value: String): Date {
    var date = Date()
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+09:00")
    try {
        date = format.parse(value)!!
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return date
}

fun Date.addYears(years: Int): Date{
    return add(Calendar.YEAR, years)
}
fun Date.addMonths(months: Int): Date {
    return add(Calendar.MONTH, months)
}
fun Date.addDays(days: Int): Date{
    return add(Calendar.DAY_OF_MONTH, days)
}
fun Date.addHours(hours: Int): Date{
    return add(Calendar.HOUR_OF_DAY, hours)
}
fun Date.addMinutes(minutes: Int): Date{
    return add(Calendar.MINUTE, minutes)
}
fun Date.addSeconds(seconds: Int): Date{
    return add(Calendar.SECOND, seconds)
}

/** A date to string using default locale */
fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

/** Get current date time using calendar instance */
fun getCurrentDateTime(): Date {
    return Calendar.getInstance().time
}

//@ExperimentalTime
        /**
         * Get the date diff in days between start and end date
         * @param startDate string start date
         * @param endDate string end date
         */
//fun getDateDiff(format: SimpleDateFormat, startDate: String, endDate: String): Long {
//    return try {
//        DurationUnit.DAYS.convert(
//            format.parse(endDate).time - format.parse(startDate).time,
//            DurationUnit.MILLISECONDS
//        )
//    } catch (e: Exception) {
//        e.printStackTrace()
//        0
//    }
//}
