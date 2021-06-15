package com.meli.challenge.model

data class Paging(
        val total: Int,
        val offset: Int,
        val limit: Int,
        val primaryResults: Int
)
