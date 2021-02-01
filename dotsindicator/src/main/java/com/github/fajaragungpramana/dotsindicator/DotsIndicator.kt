package com.github.fajaragungpramana.dotsindicator

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.viewpager.widget.ViewPager

class DotsIndicator(context: Context, attrs: AttributeSet?) : RelativeLayout(context, attrs) {

    private val mContainer: LinearLayout
    private val mDots: ArrayList<Dot>

    val dotColor: Int

    val dotCount: Int

    val dotSelectedColor: Int

    val dotSize: Float

    val dotSpacing: Float

    init {
        gravity = Gravity.CENTER

        mContainer = LinearLayout(context)
        mDots = ArrayList()

        val ta = context.obtainStyledAttributes(attrs, R.styleable.DotsIndicator)
            .apply {
                dotColor = getColor(R.styleable.DotsIndicator_dotColor, Color.LTGRAY)
                dotCount = getInt(R.styleable.DotsIndicator_dotCount, 5)
                dotSelectedColor = getColor(R.styleable.DotsIndicator_dotSelectedColor, Color.RED)
                dotSize = getDimension(R.styleable.DotsIndicator_dotSize, 32F) / 2
                dotSpacing = getDimension(R.styleable.DotsIndicator_dotSpacing, 8F) / 2
            }

        initDot()
        setDot()
        setSelectedDot(0)

        ta.recycle()
    }

    private fun initDot() {
        repeat(dotCount) { mDots.add(Dot(context)) }

        repeat(mDots.size) { i ->
            mDots[i].let {
                it.dotColor = dotColor
                it.dotSize = dotSize
                it.dotSpacing = dotSpacing
            }
        }
    }

    private fun setDot() {
        mContainer.orientation = LinearLayout.HORIZONTAL

        repeat(mDots.size) {
            mContainer.addView(
                mDots[it],
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

        addView(mContainer)
    }

    fun setWithViewPager(viewPager: ViewPager) {
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                setSelectedDot(position)
            }
        })
    }

    private fun setSelectedDot(position: Int) {
        for (i in mDots.indices)
            mDots[i].dotColor = if (i == position) dotSelectedColor else dotColor
    }

}