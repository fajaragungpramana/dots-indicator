package com.github.fajaragungpramana.dotsindicator

enum class DotStyle(
    val id: IntArray,
    val color: Int,
    val count: Int,
    val selectedColor: Int,
    val size: Int,
    val spacing: Int
) {
    DOTS(
        R.styleable.DotsIndicator,
        R.styleable.DotsIndicator_dotColor,
        R.styleable.DotsIndicator_dotCount,
        R.styleable.DotsIndicator_dotSelectedColor,
        R.styleable.DotsIndicator_dotSize,
        R.styleable.DotsIndicator_dotSpacing
    )
}