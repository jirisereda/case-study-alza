package com.case_study_alza.services.dto

import kotlinx.serialization.Serializable

@Serializable
data class CategoriesResponse(
    var data: List<CategoryItemResponse>
)

@Serializable
data class CategoryItemResponse(
    val name: String,
    val id: Long
)
