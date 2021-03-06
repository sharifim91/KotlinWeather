package com.sharifi.kotlinweather.data.model

/**
 * Created by sharifi on 9/24/17.
 */
data class Forecast(
        val id: Long,
        val date: Long,
        val description: String,
        val high: Int,
        val low: Int,
        val iconUrl: String
)

data class ForecastList(
        val id: Long,
        val city: String,
        val country: String,
        val dailyForecast: List<Forecast>) {
    operator fun get(position: Int) = dailyForecast[position]
    val size: Int
        get() = dailyForecast.size
}