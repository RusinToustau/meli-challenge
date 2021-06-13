package com.meli.challenge.model

data class Item (
    val id: String?,
    val siteID: String?,
    val title: String?,
    val seller: Seller?,
    val price: Double?,
    val currencyID: String?,
    val availableQuantity: Long?,
    val soldQuantity: Long?,
    val buyingMode: String?,
    val listingTypeId: String?,
    val stopTime: String?,
    val condition: String?,
    val permalink: String?,
    val thumbnail: String?,
    val acceptsMercadopago: Boolean?,
    val installments: Installments?,
    val address: Address?,
    val shipping: Shipping?,
    val sellerAddress: SellerAddress?,
    val attributes: List<Attribute>?,
    val categoryID: String?,
    val officialStoreId: Long?,
    val catalogProductId: String?,
    val tags: List<String>?,
    val catalogListing: Boolean?
)