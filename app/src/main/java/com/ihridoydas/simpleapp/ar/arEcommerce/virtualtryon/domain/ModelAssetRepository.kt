package com.ihridoydas.simpleapp.ar.arEcommerce.virtualtryon.domain

interface ModelAssetRepository {
    suspend fun fetchAsset(productId: Int): String
}