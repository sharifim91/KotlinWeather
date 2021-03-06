package com.sharifi.kotlinweather.toolbar

import android.support.design.widget.AppBarLayout
import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.sharifi.kotlinweather.App
import com.sharifi.kotlinweather.util.slideEnter
import com.sharifi.kotlinweather.util.slideExit
import org.jetbrains.anko.toast

/**
 * Created by sharifi on 9/9/17.
 *
 * Toolbar interface that add a Toolbar to Activity or Fragment and handles basic functionality of toolbar
 */
interface ToolbarManager {
    val toolbar: Toolbar
    var toolbarTitle: String
        get() = toolbar.title.toString()
        set(value) {
            toolbar.title = value
        }

    /**
     * add menu to toolbar
     */
    fun initToolbarMenu(menuId : Int = 0) {
        if (menuId == 0) {
            return
        }
        toolbar.inflateMenu(menuId)
        toolbar.setOnMenuItemClickListener {
            App.instance.toast(it.title)
            true
        }
    }

    fun enableHomeAsUp(up: () -> Unit) {
        toolbar.navigationIcon = createUpDrawable()
        toolbar.setNavigationOnClickListener { up() }
    }

    fun attachToScroll(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val appBar = toolbar.parent
                val scrollItem = if (appBar != null && appBar is AppBarLayout) appBar else toolbar

                if (dy > 0) scrollItem.slideExit() else scrollItem.slideEnter()


            }
        })
    }

    private fun createUpDrawable() = DrawerArrowDrawable(toolbar.context).apply {
        progress = 1f
    }


}