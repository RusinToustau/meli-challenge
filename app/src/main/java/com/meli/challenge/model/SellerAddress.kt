package com.meli.challenge.model

data class SellerAddress(
        val id: String,
        val comment: String,
        val addressLine: String,
        val zipCode: String,
        val country: City,
        val state: City,
        val city: City,
        val latitude: String,
        val longitude: String
)
