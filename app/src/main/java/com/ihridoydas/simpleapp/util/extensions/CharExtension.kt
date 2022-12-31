
package com.ihridoydas.simpleapp.util.extensions


/** Is the char an Emoji? */
@OptIn(ExperimentalStdlibApi::class)
fun Char.isEmoji() =
    Regex("[\\u2190-\\u21FF]|[\\u2600-\\u26FF]|[\\u2700-\\u27BF]|[\\u3000-\\u303F]|[\\u1F300-\\u1F64F]|[\\u1F680-\\u1F6FF]").matchAt(this.toString(),0)


/** Is half width English character */
fun Char.HalfWidthLettersAndNumbers() = Regex("^[A-z0-9]*$").matches(this.toString())
