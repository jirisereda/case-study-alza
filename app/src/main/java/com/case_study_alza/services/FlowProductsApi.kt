package com.case_study_alza.services

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FlowProductsApi(private val api: ApiService) {

    fun getProducts(productsRequest: ProductsRequest): Flow<List<ProductItem>> {
        return flow {
            api.getProducts(productsRequest).body()?.let {
                emit(it.data)
            }
        }
    }

}