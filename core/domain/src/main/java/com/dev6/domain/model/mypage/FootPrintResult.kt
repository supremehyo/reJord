package com.dev6.domain.model.mypage

data class FootPrintResult(
    val badgeCode : String,
    val createdDate : String,
    val footprintAcquirementType : String,
    val footprintAmount : Int,
    val footprintId : String,
    val parentId : String,
    val title : String
)
