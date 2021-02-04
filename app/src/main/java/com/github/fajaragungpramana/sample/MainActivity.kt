package com.github.fajaragungpramana.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.github.fajaragungpramana.dotsindicator.DotsIndicator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        val dotIndicator = findViewById<DotsIndicator>(R.id.dots_indicator)

        viewPager.adapter = Pager(this)
        dotIndicator.viewPager = viewPager

    }

}