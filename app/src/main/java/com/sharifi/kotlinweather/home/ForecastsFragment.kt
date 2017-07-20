package com.sharifi.kotlinweather.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sharifi.kotlinweather.R
import com.sharifi.kotlinweather.data.Person
import com.sharifi.kotlinweather.data.repository.Request
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread

/**
 * A placeholder fragment containing a simple view.
 */
class ForecastsFragment : Fragment() {
    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thurs 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10",
            "Sat 6/28 - TRAPPED IN WEATHER STATION - 23/18",
            "Sun 6/29 - Sunny - 20/7"
    )
    private val TAG = ForecastsFragment::class.java.simpleName

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater!!.inflate(R.layout.fragment_home, container, false)

        val forecastList = root?.findViewById(R.id.forecast_list) as RecyclerView
        forecastList.layoutManager = LinearLayoutManager(context)
        forecastList.adapter = ForecastListAdapter(items)

        return root
    }



    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val person = Person()
        person.firstName = "mohammad"
        person.lastName = "sharifi"
        Log.d(TAG, person.toString())

        doAsync {
            Request("https://refill.vasapi.click/api/last_change/").run()
            uiThread {
                context.longToast("Request Performed!")
            }
        }
    }

    companion object {

        val FRAGMENT_NAME: String? = ForecastsFragment::class.java.name

        fun newInstance(): ForecastsFragment {

            val args = Bundle()

            val fragment = ForecastsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
