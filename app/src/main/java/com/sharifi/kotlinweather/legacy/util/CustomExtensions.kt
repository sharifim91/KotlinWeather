package com.sharifi.kotlinweather.legacy.util

import android.app.Activity
import android.database.sqlite.SQLiteDatabase
import android.support.v4.app.Fragment
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.SelectQueryBuilder
import org.jetbrains.anko.internals.AnkoInternals
import java.text.DateFormat
import java.util.*

/**
 * Created by mohammad on 8/22/17.
 */

inline fun <reified T : Activity> Fragment.startActivity(vararg params: Pair<String, Any>) {
    val intent = AnkoInternals.createIntent(context, T::class.java, params)
    startActivity(intent)
}

fun <T : Any> SelectQueryBuilder.parseList(parser: (Map<String, Any?>) -> T): List<T> =
        parseList(object : MapRowParser<T> {
            override fun parseRow(columns: Map<String, Any?>): T =
                    parser(columns)
        })

fun <T : Any> SelectQueryBuilder.parseOpt(parser: (Map<String, Any?>) -> T): T? =
        parseOpt(object : MapRowParser<T> {
            override fun parseRow(columns: Map<String, Any?>): T = parser(columns)
        })

fun SQLiteDatabase.clear(tableName: String) {
    execSQL("delete from $tableName")
}

fun <K, V : Any> MutableMap<K, V?>.toVarargArray(): Array<out Pair<K, V>> =
        map({ Pair(it.key, it.value!!) }).toTypedArray()

fun SelectQueryBuilder.byId(id: Long) = whereSimple("_id = ?", id.toString())

fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM): String {
    val df = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this)
}