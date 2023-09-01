package com.ihridoydas.simpleapp.util.responsiveUI.component.dropDownMenu


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu() {

    var isExpended by remember {
        mutableStateOf(false)
    }

    var gender by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ExposedDropdownMenuBox(
            expanded = isExpended,
            onExpandedChange = { isExpended = it }
        ) {
            TextField(
                value = gender,
                onValueChange = {},
                readOnly = true,
                placeholder = {
                    Text(text = "Please Select your Gender")
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpended)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = isExpended,
                onDismissRequest = { isExpended = false }
            ) {
                DropdownMenuItem(
                    text = { Text(text = "Male") },
                    onClick = {
                        gender = "Male"
                        isExpended = false
                    }
                )
                DropdownMenuItem(
                    text = { Text(text = "Female") },
                    onClick = {
                        gender = "Female"
                        isExpended = false
                    }
                )
                DropdownMenuItem(
                    text = { Text(text = "Other") },
                    onClick = {
                        gender = "Other"
                        isExpended = false
                    }
                )

            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDropDownMenu() {
    SimpleAppTheme {
        DropDownMenu()
    }

}