package com.case_study_alza.screen.product_detail

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

data class ProductDetailState(
    val productDetail: ProductDetail? = null
)

class ProductDetailViewModel : ScreenViewModel<ProductDetailState, ProductDetailScreenArgs>(
    ProductDetailState()
) {

    override fun onArgumentsSet(screenArguments: ProductDetailScreenArgs) {

        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }

        val service = Retrofit.Builder()
            .baseUrl("https://www.alza.cz/Services/RestService.svc/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(ApiService::class.java)

        FlowApi(service).getProductDetail(screenArguments.productId)
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



