package com.meli.challenge.model

data class BaseListResponse<T> (
    val siteId: String?,
    val query: String?,
    val paging: Paging?,
    val results: List<T>
)