package com.github.fajaragungpramana.dotsindicator

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.viewpager.widget.ViewPager
import java.lang.NullPointerException

open class DotsIndicator(context: Context, attrs: AttributeSet?) : RelativeLayout(context, attrs) {

    private lateinit var mDots: ArrayList<Dot>

    private lateinit var mDotSelected: Dot

    protected var dotColor: Int

    protected var dotCount: Int

    protected var dotSelectedColor: Int

    protected var dotSize: Int

    protected var dotSpacing: Int

    var viewPager: ViewPager? = null
        set(value) {
            field = value

            onPageSelected(field)

            if (field != null) {
                removeAllViews()
                initDots()
                initDotSelected()
            }
        }

    init {
        gravity = Gravity.CENTER

        context.obtainStyledAttributes(attrs, R.styleable.DotsIndicator).also {
            dotColor = it.getColor(R.styleable.DotsIndicator_dotColor, Color.LTGRAY)
            dotCount = it.getInt(R.styleable.DotsIndicator_dotCount, 5)
            dotSelectedColor = it.getColor(R.styleable.DotsIndicator_dotSelectedColor, Color.RED)
            dotSize = it.getDimension(R.styleable.DotsIndicator_dotSize, 32F).toInt()
            dotSpacing = (it.getDimension(R.styleable.DotsIndicator_dotSpacing, 8F) / 2).toInt()
        }.recycle()

        init()
        initDots()
        initDotSelected()
    }

    private fun init() {
        mDots = ArrayList()
        mDotSelected = Dot(context).also {
            it.color = dotSelectedColor
            it.width = dotSize
            it.height = dotSize
        }
    }

    private fun initDots() {

        val dotContainer = LinearLayout(context).also {
            it.orientation = LinearLayout.HORIZONTAL
        }

        val dotParams = LayoutParams(dotSize, dotSize).also {
            it.marginStart = dotSpacing
            it.marginEnd = dotSpacing
        }

        mDots.clear()
        repeat(dotCount) {
            val dot = Dot(context).also {
                it.color = dotColor
                it.width = dotSize
                it.height = dotSize
            }
            mDots.add(dot)

            dotContainer.addView(mDots[it], dotParams)
        }

        addView(dotContainer)
    }

    private fun initDotSelected() {
        val dotSelectedParams = LayoutParams(dotSize, dotSize).also {
            it.marginStart = dotSpacing
            it.marginEnd = dotSpacing
        }

        addView(mDotSelected, dotSelectedParams)
    }

    private fun onPageSelected(viewPager: ViewPager?) {
        if (viewPager?.adapter != null) {
            dotCount = viewPager.adapter?.count ?: 0

            viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

                override fun onPageScrollStateChanged(state: Int) {}

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    onPageSelectedPosition(position)
                }

                override fun onPageSelected(position: Int) {}

            })
        } else
            throw NullPointerException("No adapter ViewPager attached!")
    }

    protected open fun onPageSelectedPosition(position: Int) {
        SpringAnimation(
            mDotSelected,
            SpringAnimation.TRANSLATION_X,
            if (position == 0) 1F else mDots[position].x - dotSpacing
        ).start()
    }

}