/*
 * Created by DavidA on 2022/08/19
 * Copyright © 2022 Cognivision Inc. All rights reserved.
 */

package com.ihridoydas.simpleapp.util.use_case.validation

import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.util.extensions.containsEmoji

/**
 * Validate a numberplate free input use case
 */
class ValidateNumberplateFree {

    companion object {
        const val MAX_LENGTH = 20
    }

    fun execute(freeInput: String): ValidationResult {
        if (freeInput.isEmpty()) {
            return ValidationResult(successful = false, errorMessage = R.string.validation_empty)
        }
        if (freeInput.isBlank()) {
            return ValidationResult(successful = false, errorMessage = R.string.cannot_enter_only_spaces_and_space_removed)
        }
        if (textTooLong(freeInput)) {
            return ValidationResult(successful = false, errorMessage = R.string.validation_field_too_long)
        }

        if (invalidChars(freeInput) ) {
            return ValidationResult(successful = false, errorMessage = R.string.validation_focus_lost_free_invalid_chars)
        }

        return ValidationResult(successful = true)
    }

    fun isBlank(freeInput: String): Boolean {
        return freeInput.isBlank()
    }

    fun textTooLong(freeInput: String): Boolean {
        return freeInput.length > MAX_LENGTH
    }

    fun invalidChars(freeInput: String) : Boolean {
        return freeInput.containsEmoji()
    }
}
