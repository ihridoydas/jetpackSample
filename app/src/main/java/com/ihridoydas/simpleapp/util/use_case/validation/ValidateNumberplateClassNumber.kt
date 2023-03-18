package com.ihridoydas.simpleapp.util.use_case.validation

import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.util.extensions.HalfWidthLettersAndNumbers
import com.ihridoydas.simpleapp.util.extensions.HalfWidthLettersAndNumbersAll
import com.ihridoydas.simpleapp.util.extensions.containsEmoji

/** Validate numberplate classnumber*/
class ValidateNumberplateClassNumber {

    companion object {
        const val MAX_LENGTH = 3
    }

    fun execute(input: String): ValidationResult {

        if (input.isEmpty()) {
            return ValidationResult(successful = true)
        }

        if (!input.HalfWidthLettersAndNumbersAll() || textTooLong(input) || input.containsEmoji()) {
            return ValidationResult(successful = false, errorMessage = R.string.validation_focus_lost_number_only)
        }

        return ValidationResult(successful = true)
    }

    fun textTooLong(input: String): Boolean {
        return input.length > MAX_LENGTH
    }

    fun isCharValid(char: Char): Boolean {
        return char.HalfWidthLettersAndNumbers()
    }
}
