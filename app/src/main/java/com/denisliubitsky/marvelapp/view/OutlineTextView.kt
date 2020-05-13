package com.denisliubitsky.marvelapp.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint.Join
import android.graphics.Paint.Style
import android.util.AttributeSet
import com.denisliubitsky.marvelapp.R

class OutlineTextView : androidx.appcompat.widget.AppCompatTextView {
    private var strokeColor: Int = Color.TRANSPARENT
    private var strokeWidth = 2

    constructor(context: Context?) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val a: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.OutlineTextView)
        strokeColor = a.getColor(R.styleable.OutlineTextView_textStrokeColor, strokeColor)
        strokeWidth =
            a.getDimensionPixelSize(R.styleable.OutlineTextView_textStrokeWidth, strokeWidth)
        a.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        val textColor = textColors
        val paint = this.paint
        paint.style = Style.STROKE
        paint.strokeJoin = Join.ROUND
        paint.strokeMiter = 10f
        this.setTextColor(strokeColor)
        paint.strokeWidth = strokeWidth.toFloat()
        super.onDraw(canvas)
        paint.style = Style.FILL
        setTextColor(textColor)
        super.onDraw(canvas)
    }
}