package com.ihridoydas.simpleapp.ar.arEcommerce.virtualtryon.data

import com.ihridoydas.simpleapp.ar.arEcommerce.virtualtryon.domain.ModelAssetRepository
import kotlinx.coroutines.delay

class ModelAssetRepositoryImpl : ModelAssetRepository {
    override suspend fun fetchAsset(productId: Int) : String {
        // return some data
        delay(2000)
        return "models/orangehandbag.glb"
    }
}