package com.ihridoydas.simpleapp.ar.arEcommerce.productdescription.domain

import com.ihridoydas.simpleapp.ar.arEcommerce.productdescription.data.Product

interface ProductRepository{
    suspend fun fetchProductMetadata(id: Int): List<Product>
}