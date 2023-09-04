package com.ihridoydas.simpleapp.ar.arEcommerce.productdescription.data

data class Product(
    val productId: Int,
    val images: List<String>,
    val description: String,
    val title: String,
    val color: String,
    val priceInCents: Int
)