package com.case_study_alza.services.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductsResponse(
    var data: List<ProductItemResponse>
)

@Serializable
data class ProductItemResponse(
    val name: String,
    val id: Long
)


