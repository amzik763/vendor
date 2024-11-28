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
import com.pampiway.vendor.components.SmallButtonBorderRounded
import com.pampiway.vendor.components.SmallButtonRounded
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
fun referandearnscreen() {
    Column(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        val couponcode by remember {
            mutableStateOf("2X9AJGME39C4")
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
                    painterResource(id = R.drawable.ic_referearn),
                    contentDescription = "image",
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .padding(24.dp),
                    contentScale = ContentScale.Fit)

                Text(text = "Earn 150 Coins!",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontFamily = mFont.fsmedium,
                        fontSize = 24.sp,
                        color = mred, textAlign = TextAlign.Center
                    ), modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth()
                )

                Text(text = "For every new user you refer",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontFamily = mFont.fsmedium,
                        fontSize = 18.sp,
                        color = lightBlack, textAlign = TextAlign.Center
                    ), modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                )

                Text(text = "share your referral link and \n" +
                        "earn 150 coins",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontFamily = mFont.fsregular,
                        fontSize = 16.sp,
                        color = darkGrey, textAlign = TextAlign.Center
                    ), modifier = Modifier
                        .padding(top = 4.dp)
                        .fillMaxWidth()
                )

            }


            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
                // Bottom button
                SmallButtonBorderRounded(onClick = {

                }, text = couponcode, cornerRadius = 60.dp)

                Spacer(modifier = Modifier.padding(bottom = 12.dp))

                SmallButtonRounded(onClick = {

                }, text = "Share", cornerRadius = 60.dp)

                Spacer(modifier = Modifier.padding(bottom = 48.dp))

            }
        }
    }
}
