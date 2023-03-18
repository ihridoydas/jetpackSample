/*
 * Created by DavidA on 2022/08/30
 * Copyright © 2022 Cognivision Inc. All rights reserved.
 */

package com.ihridoydas.simpleapp.util.use_case.validation

import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.util.extensions.HiraganaAll
import com.ihridoydas.simpleapp.util.extensions.containsEmoji

/** Validate numberplate Hiragana*/
class ValidateNumberplateHiragana {

    companion object {
        const val MAX_LENGTH = 1
    }

    fun execute(input: String): ValidationResult {

        if (input.isEmpty()) {
            return ValidationResult(successful = true)
        }

        if (!input.HiraganaAll() || textTooLong(input) || input.containsEmoji()) {
            return ValidationResult(successful = false, errorMessage = R.string.validation_focus_lost_fullwidth_only)
        }

        return ValidationResult(successful = true)
    }

    fun textTooLong(input: String): Boolean {
        return input.length > MAX_LENGTH
    }

    fun isCharValid(char: Char): Boolean {
        return char.toString().HiraganaAll()
    }
}
