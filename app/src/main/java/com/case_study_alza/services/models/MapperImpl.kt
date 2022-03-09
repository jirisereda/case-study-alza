package com.case_study_alza.services.models

import com.case_study_alza.services.dto.CategoryItemResponse
import com.case_study_alza.services.dto.ProductItemResponse

object MapperImpl :Mapper {
    override fun toCategoryItemModel(
        categoryItemResponse: CategoryItemResponse
    ): CategoryItem {
       return CategoryItem(
           id = categoryItemResponse.id,
           name = categoryItemResponse.name
       )
    }

    override fun toProductItemModel(
        productItemResponse: ProductItemResponse
    ): ProductItem {
        return ProductItem(
            id = productItemResponse.id,
            name = productItemResponse.name
            )
    }
}