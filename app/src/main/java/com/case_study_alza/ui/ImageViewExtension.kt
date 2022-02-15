package com.case_study_alza.ui

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter(
    "url",
    "placeholderDrawable",
    "errorDrawable"
)
fun ImageView.loadImage(
    url: String? = null,
    placeholderDrawable: Drawable? = null,
    errorDrawable: Drawable? = null
) {
    load(url) {
        crossfade(true)
        placeholder(placeholderDrawable)
        error(errorDrawable)
    }
}