package com.case_study_alza.services

import com.case_study_alza.services.models.MapperImpl
import kotlinx.coroutines.flow.flow
import java.lang.IllegalStateException

class FlowApi(private val api: ApiService) {

    fun getCategories() = flow {
        val response = api.getCategories()

        if (response.isSuccessful) {
            response.body()?.let {

                emit(it.data.map { categoryItemResponse ->
                    MapperImpl.toCategoryItemModel(categoryItemResponse)
                })

            }
        } else {
            throw IllegalStateException("Error occurred while fetching categories: $response")
        }
    }

    fun getProducts(productsRequest: ProductsRequest) = flow {
        val response = api.getProducts(productsRequest)

        if (response.isSuccessful) {
            response.body()?.let {
                emit(it.data.map { productItemResponse ->
                    MapperImpl.toProductItemModel(productItemResponse)
                })
            }
        } else {
            throw IllegalStateException("Error occurred while fetching products: $response")
        }
    }

    fun getProductDetail(productId: Long) = flow {

        val response = api.getProductDetail(productId.toString())

        if (response.isSuccessful) {
            response.body()?.let {
                emit(it.data)
            }
        } else {
            throw IllegalStateException("Error occurred while fetching product detail: $response")
        }
    }
}