package com.pampiway.vendor.utility

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.pampiway.vendor.ui.theme.darkGrey
import com.pampiway.vendor.ui.theme.mgreen
import com.pampiway.vendor.ui.theme.mred

@OptIn(ExperimentalMaterialApi::class)
//@Preview
@Composable
fun mDialog2(
    onDismiss: () -> Unit,
){


    Dialog(
        onDismissRequest = {
            onDismiss()
            myComponent.registerViewModel.errors?.clear()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            onClick = {},
            modifier = Modifier
                .padding(8.dp)
                .border(width = 1.dp, color = Color.White)
                .background(color = Color.White)
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(8.dp)
            ) {
                myComponent.registerViewModel.errors?.forEach { error ->
                    Text(
                        text = error.value ,
                        color = mred,
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                }
            }
        }
    }
}

@Composable
fun mDialog(
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = {
            onDismiss()
            myComponent.registerViewModel.errors?.clear()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(200.dp)
                .background(color = Color.White, shape = RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
            elevation = 8.dp
        ) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(200.dp)

            ) {
                // Close button
                IconButton(
                    onClick = {
                        onDismiss()
                        myComponent.registerViewModel.errors?.clear()
                    },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Dialog",
                        tint = Color.Gray
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(top = 24.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally // Space below the close button
                ) {
                    // Display errors
                    Text(
                        text = myComponent.registerViewModel.errorMessage.value,
                        color = darkGrey,
                        style = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        ), textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 4.dp).fillMaxWidth()
                    )

                    showLogs("ERROR:",myComponent.registerViewModel.errorMessage.value)
                }
            }
        }
    }
}

