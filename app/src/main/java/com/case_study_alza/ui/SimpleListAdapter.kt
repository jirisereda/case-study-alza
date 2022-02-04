package com.case_study_alza.ui

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

open class SimpleListAdapter<BindingType : ViewDataBinding, ItemType>(
    @LayoutRes private val layoutId: Int,
    lifeCycleOwner: LifecycleOwner,
    items: Flow<List<ItemType>>,
    private val bindView: (BindingType, ItemType) -> Unit
) : RecyclerView.Adapter<DataBindedViewHolder<BindingType, ItemType>>() {

    private var itemsList: List<ItemType> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    init {
        items
            .onEach { itemsList = it }
            .launchIn(lifeCycleOwner.lifecycleScope)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        recyclerView.setUpRecyclerView()

        (recyclerView.itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
    }

    open fun RecyclerView.setUpRecyclerView() {}

    fun getItem(position: Int): ItemType? = itemsList[position]

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataBindedViewHolder<BindingType, ItemType> =
        DataBindedViewHolder(bindView, parent.inflateView(layoutId))

    override fun getItemCount() = itemsList.size

    override fun onBindViewHolder(
        holder: DataBindedViewHolder<BindingType, ItemType>,
        position: Int
    ) {
        itemsList[position]?.let { holder.bind(it) }
    }
}
