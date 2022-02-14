package com.case_study_alza.screen.product_detail

import androidx.lifecycle.viewModelScope
import com.case_study_alza.core.ScreenViewModel
import com.case_study_alza.services.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

data class ProductDetailState(
    val productDetail: ProductDetail? = null
)

class ProductDetailViewModel @Inject constructor(
    private val apiService: ApiService
) : ScreenViewModel<ProductDetailState, ProductDetailScreenArgs>(
    ProductDetailState()
) {

    override fun onArgumentsSet(screenArguments: ProductDetailScreenArgs) {

        FlowApi(apiService).getProductDetail(screenArguments.productId)
            .onEach {
                Timber.d("DEBUG FlowProductDetailApi $it")
                currentState.next { copy(productDetail = it) }
                Timber.d("DEBUG FlowProductDetailApi $state.productDetail")
            }
            .catch {
                Timber.e("DEBUG FlowProductDetailApi Error $it.message")
            }
            .launchIn(viewModelScope)
    }
}



