package com.case_study_alza.screen.product_detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.case_study_alza.R
import com.case_study_alza.core.Screen
import com.case_study_alza.databinding.ProductDetailScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailScreen :
    Screen<ProductDetailViewModel, ProductDetailScreenBinding, ProductDetailScreenArgs>() {
    override val viewModel: ProductDetailViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.product_detail_screen
    override val dataBindViewModel: ProductDetailScreenBinding.(ProductDetailViewModel) -> Unit = {
        viewModel = it
    }
    override val screenArgs: ProductDetailScreenArgs by navArgs()

}


