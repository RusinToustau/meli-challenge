package com.meli.challenge.model

data class SpecificProduct(
        val id: String?,
        val status: String?,
        val domainId: String?,
        val permalink: String,
        val name: String?,
        val pictures: List<Picture>?,
        val shortDescription: Description?
)
