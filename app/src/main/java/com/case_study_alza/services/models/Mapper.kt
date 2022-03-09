package com.case_study_alza.services.models

import com.case_study_alza.services.dto.CategoryItemResponse
import com.case_study_alza.services.dto.ProductItemResponse

interface Mapper {
    fun toCategoryItemModel(
        categoryItemResponse: CategoryItemResponse
    ): CategoryItem


    fun toProductItemModel(
        productItemResponse: ProductItemResponse
    ): ProductItem
}