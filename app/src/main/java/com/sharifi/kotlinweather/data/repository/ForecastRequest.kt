package com.sharifi.kotlinweather.data.repository

import android.util.Log
import com.google.gson.Gson
import java.net.URL

/**
 * Created by sharifi on 7/20/17.
 */

class ForecastRequest(val zipCode: Long) {
    companion object {
        private val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private val URL = "http://api.openweathermap.org/data/2.5/" +
                "forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$URL&APPID=$APP_ID&q="
    }

    fun execute(): ForecastResult {
        val foreCastJsonStr = URL(COMPLETE_URL + zipCode).readText()
        Log.d(javaClass.simpleName, foreCastJsonStr)
        return Gson().fromJson(foreCastJsonStr, ForecastResult::class.java)
    }
}

