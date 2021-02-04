package com.github.fajaragungpramana.dotsindicator

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager

class DotsIndicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyle: Int = 0
) : BaseDots(context, attrs, defStyle) {

    override val dotStyle: DotStyle
        get() = DotStyle.DOTS

    private val mDotContainer: LinearLayout
        get() {
            field.orientation = LinearLayout.HORIZONTAL
            return field
        }

    var viewPager: ViewPager? = null
        set(value) {
            field = value

            dotCount = field?.adapter?.count

            mDotContainer.removeAllViews()
            initDots(R.drawable.bg_dot) { dot, param -> mDotContainer.addView(dot, param) }

            onDotSelected(field)
        }

    init {
        mDotContainer = LinearLayout(context)

        initDots(R.drawable.bg_dot) { dot, param -> mDotContainer.addView(dot, param) }

        addView(mDotContainer)
    }

}
