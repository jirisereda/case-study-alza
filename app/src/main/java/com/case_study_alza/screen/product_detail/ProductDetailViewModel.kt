package com.case_study_alza.screen.product_detail

import androidx.lifecycle.viewModelScope
import com.case_study_alza.core.ScreenViewModel
import com.case_study_alza.core.loadingCall
import com.case_study_alza.services.*
import com.case_study_alza.ui.views.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

data class ProductDetailState(
    val loadingState: LoadingState = LoadingState.Loading,
    val productDetail: ProductDetail? = null
)

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val apiService: ApiService
) : ScreenViewModel<ProductDetailState, ProductDetailScreenArgs>(
    ProductDetailState()
) {

    override fun onArgumentsSet(screenArguments: ProductDetailScreenArgs) {

        FlowApi(apiService).getProductDetail(screenArguments.productId)
            .loadingCall(
                { currentState.next { copy(loadingState = LoadingState.Loading) } },
                { currentState.next { copy(loadingState = LoadingState.Data) } }
            )
            .onEach {
                currentState.next { copy(productDetail = it) }
            }
            .catch {
                Timber.e("$it")
                currentState.next { copy(loadingState = LoadingState.Error) }
            }
            .launchIn(viewModelScope)
    }
}



