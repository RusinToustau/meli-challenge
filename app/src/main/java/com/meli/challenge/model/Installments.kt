package com.meli.challenge.model

data class Installments(
        val quantity: Long,
        val amount: Double,
        val rate: Double,
        val currencyID: String
)
