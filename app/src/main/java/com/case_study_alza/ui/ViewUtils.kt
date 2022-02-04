package com.case_study_alza.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes


fun ViewGroup.inflateView(@LayoutRes layoutId: Int): View = LayoutInflater.from(context).inflate(
    layoutId,
    this,
    false
)