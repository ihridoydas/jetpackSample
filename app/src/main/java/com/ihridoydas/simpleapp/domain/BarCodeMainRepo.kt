package com.ihridoydas.simpleapp.domain

import kotlinx.coroutines.flow.Flow


interface BarCodeMainRepo {

    fun startScanning(): Flow<String?>
}