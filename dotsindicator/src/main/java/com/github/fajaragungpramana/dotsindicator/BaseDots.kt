package com.github.fajaragungpramana.dotsindicator

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager

abstract class BaseDots(
    context: Context,
    attrs: AttributeSet?,
    defStyle: Int = 0
) : RelativeLayout(context, attrs, defStyle) {

    private val imageViewList = mutableListOf<ImageView>()

    protected abstract val dotStyle: DotStyle

    var dotColor: Int? = null

    var dotCount: Int? = null

    var dotSelectedColor: Int? = null

    var dotSize: Int? = null

    var dotSpacing: Int? = null

    init {
        gravity = Gravity.CENTER

        initialize(attrs)
    }

    private fun initialize(attrs: AttributeSet?) {
        context.obtainStyledAttributes(attrs, dotStyle.id).also {
            dotColor = it.getColor(dotStyle.color, Color.LTGRAY)
            dotCount = it.getInt(dotStyle.count, 5)
            dotSelectedColor = it.getColor(dotStyle.selectedColor, Color.RED)
            dotSize = (it.getDimension(dotStyle.size, 32F) / 2F).toInt()
            dotSpacing = (it.getDimension(dotStyle.spacing, 8F) / 2F).toInt()
        }.recycle()
    }

    protected fun initDots(
        dotDrawableId: Int,
        dot: (ImageView, LinearLayout.LayoutParams) -> Unit
    ) {

        val params = LinearLayout.LayoutParams(dotSize ?: 0, dotSize ?: 0)
            .also {
                it.marginStart = dotSpacing ?: 0
                it.marginEnd = dotSpacing ?: 0
            }

        repeat(dotCount ?: 0) { i ->
            val imageView = ImageView(context).also {
                it.background = ContextCompat.getDrawable(context, dotDrawableId)
            }

            (imageView.background as GradientDrawable).let {
                it.setColor(dotColor ?: 0)
                it.cornerRadius = dotSize?.toFloat() ?: 0F
            }

            imageViewList.add(imageView)

            dot(imageViewList[i], params)
        }
    }

    protected fun onDotSelected(viewPager: ViewPager?) {
        onDotSelectedPosition(0)

        viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                onDotSelectedPosition(position)
            }

        })
    }

    private fun onDotSelectedPosition(position: Int) {
        repeat(imageViewList.size) {
            (imageViewList[it].background as GradientDrawable).setColor(
                if (it == position) dotSelectedColor ?: 0 else dotColor ?: 0
            )
        }
    }

}