package com.case_study_alza.ui.views

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.databinding.BindingAdapter
import com.case_study_alza.R
import kotlinx.parcelize.Parcelize


sealed class LoadingState : Parcelable {
    @Parcelize
    object Loading : LoadingState()

    @Parcelize
    object Data : LoadingState()

    @Parcelize
    object Error : LoadingState()
}

@BindingAdapter("loadingState")
fun convertLoadingState(layout: LoadingLayout, loadingState: LoadingState?) {
    loadingState?.let { layout.setLoadingState(loadingState) }
}

class LoadingLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null, @AttrRes defStyleAttr: Int = 0, @StyleRes defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {
    private val loadingViewId: Int
    private val contentViewId: Int
    private val errorViewId: Int

    init {
        val a = context.theme.obtainStyledAttributes(
            attrs, R.styleable.LoadingLayout, 0, 0
        )

        try {
            loadingViewId = a.getResourceId(R.styleable.LoadingLayout_loadingView, 0)
            contentViewId = a.getResourceId(R.styleable.LoadingLayout_contentView, 0)
            errorViewId = a.getResourceId(R.styleable.LoadingLayout_errorView, 0)
        } finally {
            a.recycle()
        }
    }

    fun setLoadingState(loadingState: LoadingState) {
        val loadingView = findViewById<View>(loadingViewId)
        val contentView = findViewById<View>(contentViewId)
        val errorView: View? = findViewById(errorViewId)

        when (loadingState) {
            LoadingState.Loading -> {
                contentView.gone()
                errorView?.gone()
                loadingView.visible()
            }
            LoadingState.Data -> {
                contentView.visible()
                loadingView.gone()
                errorView?.gone()
            }
            is LoadingState.Error -> {
                errorView?.visible()
                loadingView.gone()
                contentView.gone()
            }
        }
    }
}