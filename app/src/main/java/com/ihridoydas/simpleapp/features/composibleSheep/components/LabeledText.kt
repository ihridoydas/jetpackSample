package com.ihridoydas.simpleapp.features.composibleSheep.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme

@Composable
fun LabeledText(label: String, body: String) {
    Text(
        buildAnnotatedString {
            withStyle(
                MaterialTheme.typography.titleMedium.toSpanStyle()
                    .copy(color = MaterialTheme.colorScheme.onBackground)
            ) {
                append(label)
            }
            withStyle(MaterialTheme.typography.bodyMedium.toSpanStyle()) {
                append(body)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    SimpleAppTheme() {
        LabeledText(label = "Label: ", body = "body body")
    }
}
