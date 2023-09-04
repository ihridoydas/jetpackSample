package com.ihridoydas.simpleapp.ar.arEcommerce.productdescription.presentation


// UI Actions are
sealed class ProductDescriptionUIAction {
    object NavigateToVirtualTryOnScreen : ProductDescriptionUIAction()
    object NavigateToAddToCartScreen : ProductDescriptionUIAction()
}
