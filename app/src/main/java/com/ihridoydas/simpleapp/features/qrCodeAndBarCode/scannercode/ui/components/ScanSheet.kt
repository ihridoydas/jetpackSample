package com.ihridoydas.simpleapp.features.qrCodeAndBarCode.scannercode.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ihridoydas.simpleapp.R
import com.ihridoydas.simpleapp.features.qrCodeAndBarCode.scannercode.model.Scan
import com.ihridoydas.simpleapp.features.qrCodeAndBarCode.scannercode.model.ScanType
import com.ihridoydas.simpleapp.ui.theme.DarkGrey
import com.ihridoydas.simpleapp.ui.theme.LightYellow

@Composable
fun ScanSheet(
    scan: Scan,
    onShareClicked: () -> Unit,
    onCopyClicked: () -> Unit,
    onWebClicked: () -> Unit,
    modifier: Modifier = Modifier,
    scanState:Map<Int, String>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        Button(
            onClick = {},
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(12.dp)
                .width(50.dp)
                .padding(top = 5.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = DarkGrey,
                contentColor = LightYellow
            )
        ) {}
        Text(
            text = stringResource(id = scan.scanFormatId),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
        if (scan.displayValue.isNotBlank()) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 4.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
//                Text(
//                    text = scan.displayValue,
//                    style = MaterialTheme.typography.body1,
//                    fontWeight = FontWeight.SemiBold,
//                    modifier = Modifier.padding(8.dp)
//                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = scanState.values.toString(),
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(modifier = Modifier.size(10.dp))
            }
        }
        Divider()
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.End),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            FloatingActionButton(
                onClick = onShareClicked,
                backgroundColor = DarkGrey,
                contentColor = LightYellow,
                modifier = Modifier.requiredSize(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = null
                )
            }
            FloatingActionButton(
                onClick = onCopyClicked,
                backgroundColor = DarkGrey,
                contentColor = LightYellow,
                modifier = Modifier.requiredSize(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ContentCopy,
                    contentDescription = null
                )
            }
            if (scan.scanType == ScanType.Url) {
                ExtendedFloatingActionButton(
                    backgroundColor = DarkGrey,
                    contentColor = LightYellow,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Public,
                            contentDescription = null
                        )
                    },
                    text = {
                        Text(
                            text = stringResource(id = R.string.scan_sheet_web_action),
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    onClick = onWebClicked
                )
            }
        }
        Spacer(modifier = Modifier.height(2.dp))
    }
}