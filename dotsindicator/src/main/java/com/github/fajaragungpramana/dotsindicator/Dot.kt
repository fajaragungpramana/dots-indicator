package com.github.fajaragungpramana.dotsindicator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.View

internal class Dot(context: Context) : View(context) {

    internal var color: Int = Color.LTGRAY

    internal var width: Int = 32
    internal var height: Int = 32

    private var mPaint: Paint
        get() {
            field.color = color
            return field
        }

    private var mRectF: RectF
        get() {
            field.let {
                it.left = 0F
                it.top = 0F
                it.right = it.left + width
                it.bottom = it.top + height
            }
            return field
        }

    init {
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mRectF = RectF()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawRoundRect(mRectF, width.toFloat(), height.toFloat(), mPaint)
    }

}