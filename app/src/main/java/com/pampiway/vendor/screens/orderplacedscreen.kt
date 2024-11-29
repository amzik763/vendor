package com.pampiway.vendor.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pampiway.vendor.R
import com.pampiway.vendor.components.SmallButton
import com.pampiway.vendor.components.SmallButtonBorder
import com.pampiway.vendor.components.inputComponent
import com.pampiway.vendor.ui.theme.darkGrey
import com.pampiway.vendor.ui.theme.lightBlack
import com.pampiway.vendor.ui.theme.lightGrey
import com.pampiway.vendor.ui.theme.mgreen
import com.pampiway.vendor.ui.theme.mred
import com.pampiway.vendor.utility.mFont
import com.pampiway.vendor.utility.myComponent
import com.pampiway.vendor.utility.myComponent.navController
import com.pampiway.vendor.utility.myComponent.registerViewModel
import kotlinx.coroutines.launch

@Composable
fun orderPlaced() {
    Column(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        val orderID by remember {
            mutableStateOf("#239587394")
        }

        val orderTime by remember {
            mutableStateOf("30 - 35 mins")
        }


        // This Column handles the content and the button separately.
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // Enable vertical scrolling
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.SpaceBetween // Ensures bottom alignment
        ) {
            // Top content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .padding(vertical = 16.dp)
            ){
                Image(
                    painterResource(id = R.drawable.ic_rateimage),
                    contentDescription = "image",
                    modifier = Modifier.padding(16.dp).fillMaxWidth().padding(24.dp),
                    contentScale = ContentScale.Fit)

                Text(text = "Order Placed!",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontFamily = mFont.fsmedium,
                        fontSize = 24.sp,
                        color = lightBlack
                    ), modifier = Modifier.padding(top = 12.dp)
                )

                //part A
                Text(text = "Order ID",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = mFont.fsmedium,
                        fontSize = 20.sp,
                        color = lightBlack
                    ), modifier = Modifier.padding(top = 12.dp, start = 36.dp)
                )
                Text(text = orderID,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontFamily = mFont.fsregular,
                        fontSize = 17.sp,
                        color = darkGrey
                    ), modifier = Modifier.padding(top = 2.dp, start = 36.dp, bottom = 6.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    Canvas(modifier = Modifier.fillMaxWidth()) {
                        val lineWidth = size.width
                        val dashWidth = 2.dp.toPx()
                        val gapWidth = 1.dp.toPx()

                        var x = 0f
                        while (x < lineWidth) {
                            drawLine(
                                color = Color.Red,
                                start = Offset(x, 0f),
                                end = Offset(x + dashWidth, 0f),
                                strokeWidth = 2.dp.toPx()
                            )
                            x += dashWidth + gapWidth
                        }
                    }
                }


                //part B
                Text(text = "Delivery Time",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontFamily = mFont.fsmedium,
                        fontSize = 20.sp,
                        color = lightBlack
                    ), modifier = Modifier.padding(top = 12.dp, start = 36.dp)
                )
                Text(text = orderTime,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontFamily = mFont.fsregular,
                        fontSize = 17.sp,
                        color = darkGrey
                    ), modifier = Modifier.padding(top = 2.dp, start = 36.dp, bottom = 6.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    Canvas(modifier = Modifier.fillMaxWidth()) {
                        val lineWidth = size.width
                        val dashWidth = 2.dp.toPx()
                        val gapWidth = 1.dp.toPx()

                        var x = 0f
                        while (x < lineWidth) {
                            drawLine(
                                color = Color.Red,
                                start = Offset(x, 0f),
                                end = Offset(x + dashWidth, 0f),
                                strokeWidth = 2.dp.toPx()
                            )
                            x += dashWidth + gapWidth
                        }
                    }
                }




            }


            // Bottom button
            SmallButtonBorder(onClick = {

            }, text = "Cancel Order")
        }
    }
}