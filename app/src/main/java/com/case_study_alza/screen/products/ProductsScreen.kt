package com.case_study_alza.screen.products

import androidx.fragment.app.viewModels
import androidx.lifecycle.asFlow
import androidx.lifecycle.map
import androidx.navigation.fragment.navArgs
import com.case_study_alza.R
import com.case_study_alza.core.Screen
import com.case_study_alza.databinding.ProductsScreenBinding

class ProductsScreen : Screen<ProductsViewModel, ProductsScreenBinding, ProductsScreenArgs>() {
    override val viewModel: ProductsViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.products_screen
    override val dataBindViewModel: ProductsScreenBinding.(ProductsViewModel) -> Unit = {
        adapter = ProductsAdapter(
            this@ProductsScreen.viewLifecycleOwner,
            it.state.map { state -> state.productItems }.asFlow(),
            it
        )
    }
    override val screenArgs: ProductsScreenArgs by navArgs()

}


