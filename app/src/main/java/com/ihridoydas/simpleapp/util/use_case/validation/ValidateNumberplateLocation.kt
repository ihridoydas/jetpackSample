package com.ihridoydas.simpleapp.util.use_case.validation

import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.util.extensions.containsEmoji
import com.ihridoydas.simpleapp.util.extensions.onlyLettersAndNumbers

/** Validate numberplate location*/
class ValidateNumberplateLocation {

    companion object {
        const val MAX_LENGTH = 10
    }

    fun execute(input: String): ValidationResult {

        if (isTextEmpty(input)) {
            return ValidationResult(successful = true)
        }

        if (!lettersAndNumbersOnly(input) || textTooLong(input) || containsEmoji(input)) {
            return ValidationResult(successful = false, errorMessage = R.string.validation_focus_lost_letter_number_only)
        }

        return ValidationResult(successful = true)
    }

    private fun lettersAndNumbersOnly(input: String): Boolean {
        return input.onlyLettersAndNumbers() // && input.onlyLettersAndNumbersFullWidth()
    }

    fun textTooLong(input: String): Boolean {
        return input.length > MAX_LENGTH
    }

    private fun containsEmoji(input: String): Boolean {
        return input.containsEmoji()
    }

    private fun isTextEmpty(input: String): Boolean {
        return input.isEmpty()
    }

    fun isCharValid(char: Char): Boolean {
        return char.isLetterOrDigit()
    }
}
