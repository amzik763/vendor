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
fun myMenu() {
    Column(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        val username by remember {
            mutableStateOf("Jyoti")
        }

        var validationTriggered by remember { mutableStateOf(false) }
        var name by remember { mutableStateOf("") }
        var phoneNumber by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var dob by remember { mutableStateOf("") }
        val scope = rememberCoroutineScope()

        // This Column handles the content and the button separately.
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // Enable vertical scrolling
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.SpaceBetween // Ensures bottom alignment
        ) {
            // Top content
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp, bottom = 6.dp, end = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(horizontalArrangement = Arrangement.Start) {
                        Image(
                            painterResource(id = R.drawable.ic_arrow),
                            contentDescription = "back",
                            modifier = Modifier
                                .size(32.dp)
                                .clickable {
                                    myComponent.navController.popBackStack()
                                },
                            contentScale = ContentScale.Fit
                        )

                        Text(
                            text = "Hey! ${username}",
                            style = TextStyle(
                                fontFamily = mFont.fsbold,
                                fontWeight = FontWeight.Bold,
                                color = lightBlack,
                                fontSize = 22.sp
                            ),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                    CustomButton2()
                }

                OptionsColumn()

                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Past Order",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = mFont.fsregular,
                    color = darkGrey,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 8.dp))
                CustomCard()

                Spacer(modifier = Modifier.height(36.dp))
            }

            // Bottom button
            SmallButtonBorder(
                onClick = {

                },
                text = "Log Out"
            )
        }
    }
}


@Composable
fun CustomButton2() {
    Box(
        modifier = Modifier
            .height(28.dp) // Set width and height
            .background(color = Color.White, shape = RoundedCornerShape(50))
            .border(1.dp, mred, RoundedCornerShape(50)), // Padding
    ) {
        Row(modifier = Modifier
            .height(28.dp)
            .padding(horizontal = 8.dp)
            .align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center, // Center elements
        ) {
            // Element A: Image
            Image(
                painter = painterResource(id = R.drawable.ic_dollar), // Replace with your image resource
                contentDescription = "Icon",
                modifier = Modifier.size(18.dp) // Set image size
            )
            Spacer(modifier = Modifier.width(2.dp)) // Space between image and text
            // Element B: Text
            Text(
                text = "150",
                color = Color.Red, // Text color
                fontSize = 13.sp, // Text size
                fontWeight = FontWeight.Normal // Regular font weight
            )
        }
    }
}

@Composable
fun OptionsColumn() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 8.dp) // Padding around the column
    ) {
        // Favourites item
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { println("Favourites clicked!") }
                .padding(vertical = 8.dp), // Space between rows
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_heartoutlined),

                    contentDescription = "Favourites",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Favourites",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = mFont.fsmedium,
                    color = lightBlack,
                    modifier = Modifier
                        .padding(horizontal = 8.dp))
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_arrowforwardsmall),
                contentDescription = "Arrow Icon",
                modifier = Modifier.size(24.dp)
            )
        }

        // Address item
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { println("Address clicked!") }
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_locationfilled),

                    contentDescription = "Address",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Address",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = mFont.fsmedium,
                    color = lightBlack,
                    modifier = Modifier
                        .padding(horizontal = 8.dp))
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_arrowforwardsmall),
                contentDescription = "Arrow Icon",
                modifier = Modifier.size(24.dp)
            )
        }

        // Share and Earn item
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { println("Share and Earn clicked!") }
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_giftoutlined),

                    contentDescription = "Share and Earn",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Share & Earn",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = mFont.fsmedium,
                    color = lightBlack,
                    modifier = Modifier
                        .padding(horizontal = 8.dp))
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_arrowforwardsmall),
                contentDescription = "Arrow Icon",
                modifier = Modifier.size(24.dp)
            )
        }

        // Settings item
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { println("Settings clicked!") }
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_settingsoutlined),
                    contentDescription = "Settings",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Settings",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = mFont.fsmedium,
                    color = lightBlack,
                    modifier = Modifier
                        .padding(horizontal = 8.dp))
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_arrowforwardsmall),
                contentDescription = "Arrow Icon",
                modifier = Modifier.size(24.dp)
            )
        }

        // Support item
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { println("Support clicked!") }
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_headphone),
                    contentDescription = "Support",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Help & Support",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = mFont.fsmedium,
                    color = lightBlack,
                    modifier = Modifier
                        .padding(horizontal = 8.dp))
            }
            Image(
                painter = painterResource(id = R.drawable.ic_callrounded),
                contentDescription = "Arrow Icon",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}


@Composable
fun CustomCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .border(
                width = 1.5.dp,
                color = lightGrey,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp) // Inner padding
    ) {
        // Element A

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {


            // Item B (Column with Title and Subtitle)
            Column {
                Text(text = "Burger King",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = mFont.fsmedium,
                    color = lightBlack,
                    modifier = Modifier
                        .padding(horizontal = 8.dp))
                Text(text = "50 INR",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = mFont.fsregular,
                    color = darkGrey,
                    modifier = Modifier
                        .padding(horizontal = 8.dp))
            }

            // Item A (Box)
            var isDelivered by remember { mutableStateOf(true) }

            Box(
                modifier = Modifier
                    .height(30.dp)
                    .background(
                        if (isDelivered) mgreen else mred, // Change background color
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { // Toggle state on click
                        isDelivered = !isDelivered
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (isDelivered) "Delivered" else "Not Delivered", // Change text
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        }


        // Element B (Dashed Line)
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 8.dp)
        ) {
            Canvas(modifier = Modifier.fillMaxWidth()) {
                val lineWidth = size.width
                val dashWidth = 5.dp.toPx()
                val gapWidth = 2.dp.toPx()

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

        // Element C (Text)
        Text(text = "This is a description Text",
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = mFont.fsregular,
            color = lightBlack,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp))

        // Element D (Buttons)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Button A
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(0.dp)
                    )
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Reorder",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = mFont.fsmedium,
                    color = lightBlack,
                    modifier = Modifier
                        .padding(horizontal = 16.dp))
            }

            Spacer(modifier = Modifier.width(36.dp))

            // Button B
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .border(
                        width = 2.dp,
                        color = Color.Red,
                        shape = RoundedCornerShape(0.dp)
                    )
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Rate Order",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = mFont.fsmedium,
                    color = mred,
                    modifier = Modifier
                        .padding(horizontal = 16.dp))
            }
        }
    }
}
