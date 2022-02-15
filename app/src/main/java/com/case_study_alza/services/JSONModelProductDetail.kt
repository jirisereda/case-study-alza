package com.case_study_alza.services

import kotlinx.serialization.Serializable

@Serializable
data class JSONModelProductDetail(
    var data: ProductDetail
)

@Serializable
data class ProductDetail(
    val name: String?,
    val id: Long?,
    val imgs : List<ProductImg>
)

@Serializable
data class ProductImg(
    val url: String?
)


