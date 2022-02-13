package com.case_study_alza.services

import kotlinx.coroutines.flow.flow

class FlowApi(private val api: ApiService) {

    fun getCategories() = flow {
        api.getCategories().body()?.let {
            emit(it.data)
        }
    }

    fun getProducts(productsRequest: ProductsRequest) = flow {
        api.getProducts(productsRequest).body()?.let {
            emit(it.data)
        }
    }

    fun getProductDetail(productId: Long) = flow {
        api.getProductDetail(productId.toString()).body()?.let {
            emit(it.data)
        }
    }
}