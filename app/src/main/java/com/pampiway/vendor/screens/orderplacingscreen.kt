package com.pampiway.vendor.screens

import android.util.Log
import android.widget.ProgressBar
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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
fun orderPlacing() {
    Column(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        val orderAddress by remember {
            mutableStateOf("Delhi Cantt 2303")
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
                Row(modifier = Modifier.fillMaxWidth().height(200.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center){

                    ProgressBar()
                }

                Text(text = "Placing your order",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontFamily = mFont.fsmedium,
                        fontSize = 24.sp,
                        color = lightBlack
                    ), modifier = Modifier.padding(top = 12.dp)
                )

                //part A
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    // Icon at the left
                    Image(
                        painter = painterResource(id = R.drawable.ic_location2), // Replace with your desired icon
                        contentDescription = "Delivery Time Icon",
                        modifier = Modifier
                            .size(24.dp) // Set icon size
                              // Space between icon and text
                    )

                    // Column for Texts (Title and Order Time)
                    Column(
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Text(
                            text = "Delivery Address",
                            textAlign = TextAlign.Start,
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = mFont.fsmedium,
                                fontSize = 20.sp,
                                color = lightBlack
                            )
                        )
                        Text(
                            text = orderAddress,
                            textAlign = TextAlign.Start,
                            style = TextStyle(
                                fontWeight = FontWeight.Normal,
                                fontFamily = mFont.fsregular,
                                fontSize = 17.sp,
                                color = darkGrey
                            ),
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                }


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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    // Icon at the left
                    Image(
                        painter = painterResource(id = R.drawable.ic_deliverytime), // Replace with your desired icon
                        contentDescription = "Delivery Time Icon",
                        modifier = Modifier
                            .size(24.dp) // Set icon size
                              // Space between icon and text
                    )

                    // Column for Texts (Title and Order Time)
                    Column(
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Text(
                            text = "Delivery Time",
                            textAlign = TextAlign.Start,
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = mFont.fsmedium,
                                fontSize = 20.sp,
                                color = lightBlack
                            )
                        )
                        Text(
                            text = orderTime,
                            textAlign = TextAlign.Start,
                            style = TextStyle(
                                fontWeight = FontWeight.Normal,
                                fontFamily = mFont.fsregular,
                                fontSize = 17.sp,
                                color = darkGrey
                            ),
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                }

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
            Column(
                modifier = Modifier
                    .padding(bottom = 48.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Cancel",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontFamily = mFont.fsmedium,
                        fontSize = 20.sp,
                        color = lightBlack
                    ), modifier = Modifier.clickable {

                    }
                )
            }
        }
    }
}
@Composable
fun AnimatedProgressBar(isLoading: Boolean) {
    // Animatable rotation value
    val rotation = remember { Animatable(0f) }

    // Start or stop rotation based on the `isLoading` state
    LaunchedEffect(isLoading) {
        if (isLoading) {
            // Continuous rotation animation
            rotation.animateTo(
                targetValue = 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 2500, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            )
        } else {
            // Reset rotation to 0 when loading stops
            rotation.snapTo(0f)
        }
    }

    if (isLoading) {
        // Progress bar container
        Box(
            modifier = Modifier
                .size(200.dp) // Progress bar size
                .graphicsLayer(rotationZ = rotation.value) // Apply rotation
                .background(Color.White, shape = CircleShape), // Optional background
            contentAlignment = Alignment.Center
        ) {
            // Circular progress indicator
            CircularProgressIndicator(
                modifier = Modifier.size(200.dp),
                color = mgreen,
                strokeWidth = 6.dp
            )
        }
    }
}


@Composable
fun ProgressBar() {
    var isLoading by remember { mutableStateOf(true) }

    // Simulate an API call
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(5000) // Wait for 5 seconds
        isLoading = false // API response received, stop loading
    }

    AnimatedProgressBar(isLoading = isLoading)

    if (!isLoading) {
        //Navigate to new screen
    }
}
