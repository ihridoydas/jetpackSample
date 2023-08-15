package com.ihridoydas.simpleapp.ar.arEcommerce.virtualtryon.presentation

import com.google.ar.core.Plane

sealed class VirtualTryOnUIEvent {
    object ModelPlaced : VirtualTryOnUIEvent()
    data class OnPlanesUpdated(val updatedPlanes: List<Plane>) : VirtualTryOnUIEvent()

    data class FetchAsset(val productId: Int) : VirtualTryOnUIEvent()
}