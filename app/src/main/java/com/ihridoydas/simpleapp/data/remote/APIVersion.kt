package com.ihridoydas.simpleapp.data.remote

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class APIVersion(val version: Float = APIVersions.VERSION_DOT1)
