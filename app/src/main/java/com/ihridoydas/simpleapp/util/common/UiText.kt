
package com.ihridoydas.simpleapp.util.common

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

/**
 * Use string resource when no context available (ex ViewModel)
 * 文字列のラッパークラス.
 * 特長：StringResourceのStringはViewModel内から呼び出すことが可能になる
 *
 *
 */
sealed class UiText {
    data class DynamicString(val value:String): UiText()
    class StringResource(
        @StringRes val resId: Int,
        vararg val args: Any
    ): UiText()

    @Composable
    fun asString(): String {
        return when(this) {
            is DynamicString -> value
            is StringResource -> stringResource(id = resId, formatArgs = *args)
        }
    }

    fun asString(context: Context): String {
        return when(this) {
            is DynamicString -> value
            is StringResource -> context.getString(resId, *args)
        }
    }
}
