package com.case_study_alza.services

import kotlinx.serialization.Serializable

@Serializable
data class JSONModel(
    var data: List<CategoryItem>
)

@Serializable
data class CategoryItem(
    val name: String?,
    val id: Long?
)
