package com.ihridoydas.simpleapp.domain.barCode

import kotlinx.coroutines.flow.Flow


interface BarCodeMainRepo {

    fun startScanning(): Flow<String?>
}