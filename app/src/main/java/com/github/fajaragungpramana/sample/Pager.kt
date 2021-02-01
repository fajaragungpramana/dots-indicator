package com.github.fajaragungpramana.sample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter

class Pager(private val context: Context) : PagerAdapter() {

    override fun getCount() = 8

    override fun isViewFromObject(view: View, `object`: Any) = view == `object` as LinearLayout

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_pager, container, false)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

}