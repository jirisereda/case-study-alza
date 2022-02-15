package com.case_study_alza.services

import kotlinx.serialization.Serializable

@Serializable
data class JSONModelProducts(
    var data: List<ProductItem>
)

@Serializable
data class ProductItem(
    val name: String?,
    val id: Long?
)


