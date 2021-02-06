package com.github.fajaragungpramana.dotsindicator

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import java.lang.NullPointerException

open class DotsIndicator(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    protected val mDotList: ArrayList<View>

    protected val dotColor: Int

    protected var dotCount: Int

    protected val dotSelectedColor: Int

    protected val dotSize: Float

    protected val dotSpacing: Float

    var viewPager: ViewPager? = null
        set(value) {
            field = value

            onPageSelected(field) { onPage(it) }
        }

    init {
        context.obtainStyledAttributes(attrs, R.styleable.DotsIndicator).also {
            dotColor = it.getColor(R.styleable.DotsIndicator_dotColor, Color.LTGRAY)
            dotCount = it.getInt(R.styleable.DotsIndicator_dotCount, 5)
            dotSelectedColor = it.getColor(R.styleable.DotsIndicator_dotSelectedColor, Color.RED)
            dotSize = it.getDimension(R.styleable.DotsIndicator_dotSize, 32F)
            dotSpacing = it.getDimension(R.styleable.DotsIndicator_dotSpacing, 8F)
        }.recycle()

        mDotList = ArrayList()

        initLayout()
        initDots()
    }

    private fun initLayout() {
        gravity = Gravity.CENTER
        orientation = HORIZONTAL
    }

    private fun initDots() {

        repeat(dotCount) { i ->
            val dot = View(context).also {
                it.background = ContextCompat.getDrawable(context, R.drawable.bg_dot)
            }

            (dot.background as GradientDrawable).also {
                it.setColor(dotColor)
                it.cornerRadius = dotSize
            }

            val param = LayoutParams(dotSize.toInt(), dotSize.toInt()).also {
                it.marginStart = dotSpacing.toInt() / 2
                it.marginEnd = dotSpacing.toInt() / 2
            }

            mDotList.add(dot)

            addView(mDotList[i], param)
        }

        onPage(0)
    }

    private fun onPageSelected(viewPager: ViewPager?, position: (Int) -> Unit) {
        viewPager?.also {

            if (it.adapter != null) {
                dotCount = it.adapter?.count ?: 0

                removeAllViews()
                initDots()

                it.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

                    override fun onPageScrollStateChanged(state: Int) {}

                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                    }

                    override fun onPageSelected(position: Int) {
                        position(position)
                    }

                })

            } else
                throw NullPointerException("No adapter ViewPager attached!")
        }
    }

    protected open fun onPage(position: Int) {
        repeat(mDotList.size) {
            (mDotList[it].background as GradientDrawable).setColor(
                if (it == position) dotSelectedColor else dotColor
            )
        }
    }

}