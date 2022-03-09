package com.case_study_alza.services

import com.case_study_alza.services.dto.CategoriesResponse
import com.case_study_alza.services.dto.ProductsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("v1/floors")
    suspend fun getCategories(): Response<CategoriesResponse>

    @POST("v2/products")
    suspend fun getProducts(@Body products: ProductsRequest): Response<ProductsResponse>

    @GET("v13/product/{id}")
    suspend fun getProductDetail(@Path("id") productId: String): Response<JSONModelProductDetail>
}