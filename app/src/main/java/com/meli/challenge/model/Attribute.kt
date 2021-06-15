package com.meli.challenge.model

data class Attribute(
        val name: String,
        val valueID: String,
        val valueName: String,
        val valueStruct: Any?,
        val attributeGroupId: String,
        val attributeGroupName: String,
        val source: Long,
        val id: String
)
