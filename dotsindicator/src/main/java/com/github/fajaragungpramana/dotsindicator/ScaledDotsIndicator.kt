package com.github.fajaragungpramana.dotsindicator

import android.content.Context
import android.util.AttributeSet

class ScaledDotsIndicator(context: Context, attrs: AttributeSet?) : DotsIndicator(context, attrs) {

    override fun onPage(position: Int) {
        super.onPage(position)

        val scaledDotSize = dotSize.toInt() + ((50 * dotSize) / 100).toInt()

        repeat(mDotList.size) {
            mDotList[it].layoutParams =
                if (it == position)
                    onDotSelectedScale(scaledDotSize, scaledDotSize)
                else
                    onDotSelectedScale(dotSize.toInt(), dotSize.toInt())
        }

    }

    private fun onDotSelectedScale(width: Int, height: Int) = LayoutParams(width, height).also {
        it.marginStart = dotSpacing.toInt() / 2
        it.marginEnd = dotSpacing.toInt() / 2
    }

}