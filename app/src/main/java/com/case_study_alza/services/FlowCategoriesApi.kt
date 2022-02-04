package com.case_study_alza.services

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FlowCategoriesApi(private val api: ApiService) {


    fun getCategories(): Flow<List<CategoryItem>> {
        return flow {

            api.getCategories().body()?.let {
                emit(it.data)
            }


//            val response = api.getCategories()
//
//            response.isSuccessful

//            val response = api.getResponse()
//            if (response.isSuccessful) {
//                emit(api.getCategories().data)
//            }
//            else {
//                val msg = response.errorBody()?.string()
//                response.errorBody()?.close()
//                Timber.d(msg.toString())
//
//            }
        }

    }


}