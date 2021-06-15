package com.meli.challenge.views.specificproduct.repository

import com.meli.challenge.core.basemodel.Result
import com.meli.challenge.model.Item

interface SpecificProductRepository {

    suspend fun getSpecificProduct(id: String): Result<Item>

}