package com.ihridoydas.simpleapp.ar.arEcommerce.virtualtryon.presentation

data class VirtualTryOnViewState(
    val modelPlaced: Boolean = false,
    val readyToPlaceModel: Boolean = false,
    val downloadingAsset: Boolean = false,
    val modelAsset: String? = null
)
