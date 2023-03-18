package com.ihridoydas.simpleapp.util.use_case.validation

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: Int? = null
)
