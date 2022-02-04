package com.case_study_alza.services

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("v1/floors")
    suspend fun getCategories(): Response<JSONModel>
}