package com.ihridoydas.simpleapp.features.countrypicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.features.countrypicker.model.Country
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SampleCountryPicker() {
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf<Country?>(null) }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
        Box {
            CountryTextField(
                label = stringResource(R.string.select_country_text),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp, start = 40.dp, end = 40.dp),
                selectedCountry = selectedCountry,
                defaultCountry = countryList(LocalContext.current).firstOrNull { it.code == "IN" },
                onShowCountryPicker = {
                    openBottomSheet = true
                }, isPickerVisible = openBottomSheet
            )
        }

        if (openBottomSheet) {
            CountryPickerBottomSheet(
                bottomSheetTitle = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        text = stringResource(R.string.select_country_text),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                containerColor = Color.White,
                onItemSelected = {
                    selectedCountry = it
                    openBottomSheet = false
                }, onDismissRequest = {
                    openBottomSheet = false
                }
            )
        }
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimpleAppTheme {
        SampleCountryPicker()
    }
}