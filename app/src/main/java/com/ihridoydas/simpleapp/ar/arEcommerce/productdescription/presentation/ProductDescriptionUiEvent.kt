package com.ihridoydas.simpleapp.ar.arEcommerce.productdescription.presentation

sealed class ProductDescriptionUiEvent {
    object OnAddToCartTap : ProductDescriptionUiEvent()
    object OnVirtualTryOnTap : ProductDescriptionUiEvent()
    data class FetchProductData(val productId: Int) : ProductDescriptionUiEvent()
}