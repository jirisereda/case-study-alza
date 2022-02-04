package com.case_study_alza.ui

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class RecyclerItemBinding<BindingType : ViewDataBinding, T>(val bind: (BindingType, T) -> Unit)

class DataBindedViewHolder<BindingType : ViewDataBinding, ItemType>(
    private val binding: RecyclerItemBinding<BindingType, out ItemType>,
    view: View
) : RecyclerView.ViewHolder(view) {
    constructor(bindView: (BindingType, ItemType) -> Unit, view: View) : this(
        RecyclerItemBinding(bindView), view
    )

    private val viewDataBinding: BindingType? = DataBindingUtil.bind(view) as? BindingType

    fun bind(item: ItemType) {
        viewDataBinding?.let {
            @Suppress("UNCHECKED_CAST")
            (binding as RecyclerItemBinding<BindingType, ItemType>).bind(it, item)
            it.executePendingBindings()
        }
    }
}
