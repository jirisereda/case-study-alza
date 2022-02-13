package com.case_study_alza.services
//https://johncodeos.com/how-to-parse-json-with-retrofit-converters-using-kotlin/

import kotlinx.serialization.Serializable

@Serializable
data class JSONModelProductDetail(

    // Use @SerializedName(" ") for the Gson converter
    // @field:Json(name = " ") for the Moshi converter
    // @SerialName(" ") for the Kotlinx Serialization converter
    // @JsonProperty(" ") for the Jackson converter

    var data: ProductDetail
)

@Serializable
data class ProductDetail(

    val name: String?,
    val id: Long?
)


