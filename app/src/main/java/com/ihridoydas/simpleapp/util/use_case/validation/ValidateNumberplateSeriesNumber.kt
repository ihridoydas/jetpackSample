package com.ihridoydas.simpleapp.util.use_case.validation

import androidx.core.text.isDigitsOnly
import com.ihridoydas.simpleapp.util.extensions.containsEmoji
import com.ihridoydas.simpleapp.R


/** Validate numberplate series number*/
class ValidateNumberplateSeriesNumber {

    companion object {
        const val MAX_LENGTH = 4
        const val ALL_ZEROS = "0000"
    }

    fun execute(input: String): ValidationResult {

        if (input.isEmpty()) {
            return ValidationResult(successful = true)
        }

        // 0のみの場合は"0000"に変換
        var fillNumbers = input
        if (input == "0" || input == "00" || input == "000") {
            fillNumbers = ALL_ZEROS
        }
        // "0000"の場合
        if (isAllZeros(fillNumbers)) {
            return ValidationResult(successful = false, errorMessage = R.string.validation_0000)
        }

        // 数字以外が入力されている場合
        if (!input.isDigitsOnly()) {
            return ValidationResult(successful = false,errorMessage = R.string.validation_only_numbers_can_be_entered)
        }

        if (textTooLong(input) || input.containsEmoji()) {
            return ValidationResult(successful = false, errorMessage = R.string.validation_focus_lost_series_number)
        }

        return ValidationResult(successful = true)
    }

    fun textTooLong(input: String): Boolean {
        return input.length > MAX_LENGTH
    }

    fun isCharValid(char: Char): Boolean {
        return char.isDigit()
    }

    fun isAllZeros(input:String) : Boolean {
        return input == ALL_ZEROS
    }
}
