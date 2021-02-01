package com.github.fajaragungpramana.dotsindicator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

internal class Dot(context: Context) : View(context) {

    private val mDotPaint: Paint
        get() {
            field.color = dotColor
            return field
        }

    var dotColor = Color.LTGRAY
        set(value) {
            field = value
            postInvalidate()
        }

    var dotSize: Float = 32F

    var dotSpacing: Float = 8F

    init {
        mDotPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val canvasWidth = (dotSize + dotSpacing) * 2
        val canvasHeight = dotSize * 2

        setMeasuredDimension(canvasWidth.toInt(), canvasHeight.toInt())
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawCircle(
            width / 2F,
            height / 2F,
            dotSize,
            mDotPaint
        )

    }

}