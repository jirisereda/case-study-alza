package com.case_study_alza.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.graphics.drawable.InsetDrawable
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.case_study_alza.R
import kotlin.math.max

class DividerItemDecorator(context: Context, orientation: Int, drawableId: Int) :
    DividerItemDecoration(context, orientation) {
    private val dividerDrawable: Drawable? = ContextCompat.getDrawable(context, drawableId)
    private val inset = context.resources.getDimensionPixelSize(R.dimen.screen_margin)
    private val insetDivider = InsetDrawable(dividerDrawable, inset, 0, inset, 0)

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        parent.adapter?.let { adapter ->
            parent.children.take(max(0, adapter.itemCount - 1)).forEach {
                val top = it.bottom + (it.layoutParams as RecyclerView.LayoutParams).bottomMargin
                insetDivider.apply {
                    setBounds(
                        parent.paddingLeft,
                        top,
                        parent.width - parent.paddingRight,
                        top + intrinsicHeight
                    )
                    draw(canvas)
                }

            }
        }
    }
}