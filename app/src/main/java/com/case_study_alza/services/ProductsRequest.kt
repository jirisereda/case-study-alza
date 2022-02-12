package com.case_study_alza.services

import kotlinx.serialization.Serializable

@Serializable
data class ProductsRequest(
    val filterParameters: FilterParameters
)

@Serializable
data class FilterParameters(
    val id: Long
)
