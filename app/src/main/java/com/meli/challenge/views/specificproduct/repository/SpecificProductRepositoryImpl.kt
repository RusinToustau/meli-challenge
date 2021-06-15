package com.meli.challenge.views.specificproduct.repository

import com.meli.challenge.core.deserializer.WrapperResponse
import com.meli.challenge.views.specificproduct.service.SpecificProductService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SpecificProductRepositoryImpl(private val service: SpecificProductService) : SpecificProductRepository {
    override suspend fun getSpecificProduct(id: String) = withContext(Dispatchers.IO) {
        return@withContext WrapperResponse.mapResponse(service.getSpecificProduct(id))
    }
}