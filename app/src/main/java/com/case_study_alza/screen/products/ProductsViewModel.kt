package com.case_study_alza.screen.products

import androidx.lifecycle.viewModelScope
import com.case_study_alza.core.ScreenViewModel
import com.case_study_alza.services.*
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import timber.log.Timber

data class ProductsState(
    val productItems: List<ProductItem> = emptyList(),
    val categoryId: Long = 0
)

class ProductsViewModel : ScreenViewModel<ProductsState, ProductsScreenArgs>(
    ProductsState()
) {

    override fun onArgumentsSet(screenArguments: ProductsScreenArgs) {

        currentState.next {
            copy(
                categoryId = screenArguments.categoryId
            )
        }

        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }

        val service = Retrofit.Builder()
            .baseUrl("https://www.alza.cz/Services/RestService.svc/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(ApiService::class.java)

        val productsRequest = ProductsRequest(FilterParameters(currentState.categoryId))

        FlowProductsApi(service).getProducts(productsRequest)
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
    }
}



