package com.dev6.model.mypage

data class FootPrintResultDTO(
    val badgeCode : String,
    val createdDate : String,
    val footprintAcquirementType : String,
    val footprintAmount : Int,
    val footprintId : String,
    val parentId : String,
    val title : String
)
