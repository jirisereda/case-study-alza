package com.case_study_alza.services

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FlowCategoriesApi(private val api: ApiService) {

    fun getCategories(): Flow<List<CategoryItem>> {
        return flow {
            api.getCategories().body()?.let {
                emit(it.data)
            }
        }
    }

}