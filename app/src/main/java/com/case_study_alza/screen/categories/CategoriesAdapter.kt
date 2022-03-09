package com.case_study_alza.screen.categories

import androidx.lifecycle.LifecycleOwner

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.case_study_alza.R
import com.case_study_alza.databinding.ItemCategoryBinding
import com.case_study_alza.services.models.CategoryItem
import com.case_study_alza.ui.DividerItemDecorator
import com.case_study_alza.ui.SimpleListAdapter
import kotlinx.coroutines.flow.Flow

class CategoriesAdapter(
    lifecycleOwner: LifecycleOwner,
    categoryItems: Flow<List<CategoryItem>>,
    viewModel: CategoriesViewModel
) : SimpleListAdapter<ItemCategoryBinding, CategoryItem>(
    R.layout.item_category,
    lifecycleOwner,
    categoryItems,
    { viewDataBinding, categoryItem ->
        viewDataBinding.category = categoryItem
        viewDataBinding.viewModel = viewModel
    }
) {

    override fun RecyclerView.setUpRecyclerView() {
        addItemDecoration(
            DividerItemDecorator(
                context,
                DividerItemDecoration.VERTICAL,
                R.drawable.divider
            )
        )
    }
}