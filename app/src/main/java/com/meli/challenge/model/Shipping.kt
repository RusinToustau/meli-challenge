package com.meli.challenge.model

data class Shipping(
        val freeShipping: Boolean,
        val mode: String,
        val tags: List<Any?>,
        val logisticType: String,
        val storePickUp: Boolean
)
