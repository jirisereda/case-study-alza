package com.case_study_alza.screen.products

import androidx.lifecycle.viewModelScope
import com.case_study_alza.MainGraphDirections
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

data class ProductsState(
    val loadingState: LoadingState = LoadingState.Loading,
    val productItems: List<ProductItem> = emptyList()
)

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val apiService: ApiService
) : ScreenViewModel<ProductsState, ProductsScreenArgs>(
    ProductsState()
) {

    override fun onArgumentsSet(screenArguments: ProductsScreenArgs) {

        val productsRequest = ProductsRequest(FilterParameters(screenArguments.categoryId))

        FlowApi(apiService).getProducts(productsRequest)
            .loadingCall(
                { currentState.next { copy(loadingState = LoadingState.Loading) } },
                { currentState.next { copy(loadingState = LoadingState.Data) } }
            )
            .onEach {
                currentState.next { copy(productItems = it) }
            }
            .catch {
                Timber.e("$it")
                currentState.next { copy(loadingState = LoadingState.Error) }
            }
            .launchIn(viewModelScope)
    }

    fun selectProduct(id: Long) {
        MainGraphDirections.actionProductDetailScreen(id).navigate()
    }
}



