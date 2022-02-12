package com.case_study_alza.services

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("v1/floors")
    suspend fun getCategories(): Response<JSONModel>


    @POST("v2/products")
    suspend fun getProducts(@Body products: ProductsRequest): Response<JSONModelProducts>
//    suspend fun getProducts(@Body products: RequestBody): Response<JSONModelProducts>
}