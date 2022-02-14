package com.case_study_alza.screen.products

import androidx.lifecycle.viewModelScope
import com.case_study_alza.MainGraphDirections
import com.case_study_alza.core.ScreenViewModel
import com.case_study_alza.services.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

data class ProductsState(
    val productItems: List<ProductItem> = emptyList()
)

class ProductsViewModel @Inject constructor(
    private val apiService: ApiService
) : ScreenViewModel<ProductsState, ProductsScreenArgs>(
    ProductsState()
) {

    override fun onArgumentsSet(screenArguments: ProductsScreenArgs) {

        val productsRequest = ProductsRequest(FilterParameters(screenArguments.categoryId))

        FlowApi(apiService).getProducts(productsRequest)
            .onEach {
                Timber.d("DEBUG FlowProductsApi $it")
                currentState.next { copy(productItems = it) }
                Timber.d("DEBUG productItems $state.productItems")
            }
            .catch {
                Timber.e("DEBUG FlowProductsApi Error $it.message")
            }
            .launchIn(viewModelScope)
    }

    fun selectProduct(id: Long) {
        Timber.d("DEBUG product selected $id")
        MainGraphDirections.actionProductDetailScreen(id).navigate()
    }
}



