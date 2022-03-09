package com.case_study_alza.screen.products

import androidx.lifecycle.LifecycleOwner

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.case_study_alza.R
import com.case_study_alza.databinding.ItemProductBinding
import com.case_study_alza.services.models.ProductItem
import com.case_study_alza.ui.DividerItemDecorator
import com.case_study_alza.ui.SimpleListAdapter
import kotlinx.coroutines.flow.Flow

class ProductsAdapter(
    lifecycleOwner: LifecycleOwner,
    productItems: Flow<List<ProductItem>>,
    viewModel: ProductsViewModel
) : SimpleListAdapter<ItemProductBinding, ProductItem>(
    R.layout.item_product,
    lifecycleOwner,
    productItems,
    { viewDataBinding, productItem ->
        viewDataBinding.product = productItem
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