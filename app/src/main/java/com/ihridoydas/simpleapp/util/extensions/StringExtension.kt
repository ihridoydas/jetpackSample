
package com.ihridoydas.simpleapp.util.extensions

import java.security.MessageDigest

/* Get a string at index or default if doesnt exist**/
fun List<String>.getOrDefault(index: Int, default :String) : String {
    return this.getOrNull(index) ?: default
}

/**
 * バリデーションチェック
 * @param pattern 正規表現
 * @return 一致する場合はtrue、それ以外はfalseを返す。
 */
fun String.matcherValidation(pattern: Regex) = pattern.containsMatchIn(this)

/** String contains only half width letters and numbers */
fun String.onlyLettersAndNumbers() = all { it.isLetterOrDigit() }

/** String contains only full width letters and numbers */
// uses unicode regex range to validate see: https://0g0.org/category/FF00-FFEF/1/
fun String.onlyLettersAndNumbersFullWidth() = Regex("(?:[\\uFF21-\\uFF3A]\\uFF10-\\uFF19]*)").matches(this)

/** Is the String an Emoji? */
@OptIn(ExperimentalStdlibApi::class)
fun String.containsEmoji() =
    Regex("(?:[\\uD83C\\uDF00-\\uD83D\\uDDFF]|[\\uD83E\\uDD00-\\uD83E\\uDDFF]|[\\uD83D\\uDE00-\\uD83D\\uDE4F]|[\\uD83D\\uDE80-\\uD83D\\uDEFF]|[\\u2600-\\u26FF]\\uFE0F?|[\\u2700-\\u27BF]\\uFE0F?|\\u24C2\\uFE0F?|[\\uD83C\\uDDE6-\\uD83C\\uDDFF]{1,2}|[\\uD83C\\uDD70\\uD83C\\uDD71\\uD83C\\uDD7E\\uD83C\\uDD7F\\uD83C\\uDD8E\\uD83C\\uDD91-\\uD83C\\uDD9A]\\uFE0F?|[\\u0023\\u002A\\u0030-\\u0039]\\uFE0F?\\u20E3|[\\u2194-\\u2199\\u21A9-\\u21AA]\\uFE0F?|[\\u2B05-\\u2B07\\u2B1B\\u2B1C\\u2B50\\u2B55]\\uFE0F?|[\\u2934\\u2935]\\uFE0F?|[\\u3030\\u303D]\\uFE0F?|[\\u3297\\u3299]\\uFE0F?|[\\uD83C\\uDE01\\uD83C\\uDE02\\uD83C\\uDE1A\\uD83C\\uDE2F\\uD83C\\uDE32-\\uD83C\\uDE3A\\uD83C\\uDE50\\uD83C\\uDE51]\\uFE0F?|[\\u203C\\u2049]\\uFE0F?|[\\u25AA\\u25AB\\u25B6\\u25C0\\u25FB-\\u25FE]\\uFE0F?|[\\u00A9\\u00AE]\\uFE0F?|[\\u2122\\u2139]\\uFE0F?|\\uD83C\\uDC04\\uFE0F?|\\uD83C\\uDCCF\\uFE0F?|[\\u231A\\u231B\\u2328\\u23CF\\u23E9-\\u23F3\\u23F8-\\u23FA]\\uFE0F?)+").containsMatchIn(this)

/** Remove emoji from String */
@OptIn(ExperimentalStdlibApi::class)
fun String.removeEmoji() =
    Regex("(?:[\\uD83C\\uDF00-\\uD83D\\uDDFF]|[\\uD83E\\uDD00-\\uD83E\\uDDFF]|[\\uD83D\\uDE00-\\uD83D\\uDE4F]|[\\uD83D\\uDE80-\\uD83D\\uDEFF]|[\\u2600-\\u26FF]\\uFE0F?|[\\u2700-\\u27BF]\\uFE0F?|\\u24C2\\uFE0F?|[\\uD83C\\uDDE6-\\uD83C\\uDDFF]{1,2}|[\\uD83C\\uDD70\\uD83C\\uDD71\\uD83C\\uDD7E\\uD83C\\uDD7F\\uD83C\\uDD8E\\uD83C\\uDD91-\\uD83C\\uDD9A]\\uFE0F?|[\\u0023\\u002A\\u0030-\\u0039]\\uFE0F?\\u20E3|[\\u2194-\\u2199\\u21A9-\\u21AA]\\uFE0F?|[\\u2B05-\\u2B07\\u2B1B\\u2B1C\\u2B50\\u2B55]\\uFE0F?|[\\u2934\\u2935]\\uFE0F?|[\\u3030\\u303D]\\uFE0F?|[\\u3297\\u3299]\\uFE0F?|[\\uD83C\\uDE01\\uD83C\\uDE02\\uD83C\\uDE1A\\uD83C\\uDE2F\\uD83C\\uDE32-\\uD83C\\uDE3A\\uD83C\\uDE50\\uD83C\\uDE51]\\uFE0F?|[\\u203C\\u2049]\\uFE0F?|[\\u25AA\\u25AB\\u25B6\\u25C0\\u25FB-\\u25FE]\\uFE0F?|[\\u00A9\\u00AE]\\uFE0F?|[\\u2122\\u2139]\\uFE0F?|\\uD83C\\uDC04\\uFE0F?|\\uD83C\\uDCCF\\uFE0F?|[\\u231A\\u231B\\u2328\\u23CF\\u23E9-\\u23F3\\u23F8-\\u23FA]\\uFE0F?)+").replace(this,"")


/** Remove newline from String */
fun String.removeNewline() = this.replace("\n", "")

/** Remove emoji, newline from String */
fun String.clean() = this.removeEmoji().removeNewline()

fun String.HiraganaAll() = Regex("([\\u3040-\\u309F]+)").matches(this)

fun String.HalfWidthLettersAndNumbersAll() = Regex("^[A-z0-9]*$").matches(this)

/**
 * sha256でハッシュ化
 */
fun String.toSha256() =
    MessageDigest.getInstance("SHA-256")
        .digest(this.toByteArray())
        .joinToString(separator = "") {
            "%02x".format(it)
        }

/** Wraps string with: (番号:string) */
fun String.errorIdFormat() = "(番号:$this)"

/** Wrap string with str1 and str2*/
fun String.wrap(str1: String, str2: String) = "$str1$this$str2"
