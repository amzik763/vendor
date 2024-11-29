package com.pampiway.vendor.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.pampiway.vendor.R
import com.pampiway.vendor.components.InputText
import com.pampiway.vendor.components.InputTextLarge
import com.pampiway.vendor.components.InputTextWithIcon
import com.pampiway.vendor.components.SmallButton
import com.pampiway.vendor.components.SmallButtonBorder
import com.pampiway.vendor.ui.theme.darkGrey
import com.pampiway.vendor.ui.theme.lightBlack
import com.pampiway.vendor.ui.theme.mblue
import com.pampiway.vendor.ui.theme.medGrey
import com.pampiway.vendor.ui.theme.mred
import com.pampiway.vendor.utility.mFont
import com.pampiway.vendor.utility.myComponent
import com.pampiway.vendor.utility.myComponent.navController

@Composable
fun bookingScreen(navController: NavHostController) {

    val shopName = remember {
        mutableStateOf("Shree Ram Travels")
    }
    val name = remember {
        mutableStateOf("Shree Ram Travels")
    }
    val address = remember {
        mutableStateOf("Shree Ram Travels")
    }
    val aboutus = remember {
        mutableStateOf("Shree Ram Travels")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(vertical = 16.dp, horizontal = 12.dp), verticalArrangement = Arrangement.SpaceBetween
    ) {
        
        Column() {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {

                Text(
                    text = shopName.value,
                    style = TextStyle(
                        fontFamily = mFont.fsbold,
                        fontWeight = FontWeight.Bold,
                        color = lightBlack,
                        fontSize = 26.sp, textAlign = TextAlign.Center
                    ), modifier = Modifier
                        .padding(start = 32.dp)
                        .fillMaxWidth(0.9f)
                )

                Image(
                    painterResource(id = R.drawable.ic_optiondot),
                    contentDescription = "dots",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            myComponent.navController.popBackStack()
                        },
                    contentScale = ContentScale.Fit
                )
            }
            Divider(
                color = Color.White,              // Set the color of the divider
                thickness = 4.dp,                // Set the thickness of the divider
                modifier = Modifier.fillMaxWidth() // Make the divider take full width
            )
            Spacer(modifier = Modifier.height(4.dp))
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Bookings",
                    style = TextStyle(
                        fontFamily = mFont.fsregular,
                        fontWeight = FontWeight.Normal,
                        color = darkGrey,
                        fontSize = 15.sp, textAlign = TextAlign.Center
                    ), modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(4.dp))
                Canvas(modifier = Modifier.fillMaxWidth(0.2f)) {
                    val lineWidth = size.width
                    val dashWidth = 2.dp.toPx()
                    val gapWidth = 1.dp.toPx()

                    var x = 0f
                    while (x < lineWidth) {
                        drawLine(
                            color = medGrey,
                            start = Offset(x, 0f),
                            end = Offset(x + dashWidth, 0f),
                            strokeWidth = 2.dp.toPx()
                        )
                        x += dashWidth + gapWidth
                    }
                }
            }


            Details(name.value,address.value,aboutus.value)

            
            
        }
        
        SmallButton(onClick = {

        }, text =  "Call")
        

    }

}


@Composable fun Details(name:String, address:String, about:String){
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
        Text(
            text = "Owner:-",
            style = TextStyle(
                fontFamily = mFont.fssemibold,
                fontWeight = FontWeight.SemiBold,
                color = mred,
                fontSize = 20.sp
            ), modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = name,
            style = TextStyle(
                fontFamily = mFont.fsmedium,
                fontWeight = FontWeight.Medium,
                color = lightBlack,
                fontSize = 16.sp
            ), modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Address:-",
            style = TextStyle(
                fontFamily = mFont.fssemibold,
                fontWeight = FontWeight.SemiBold,
                color = mred,
                fontSize = 20.sp
            ), modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = address,
            style = TextStyle(
                fontFamily = mFont.fsmedium,
                fontWeight = FontWeight.Medium,
                color = lightBlack,
                fontSize = 16.sp
            ), modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "About Us:-",
            style = TextStyle(
                fontFamily = mFont.fssemibold,
                fontWeight = FontWeight.SemiBold,
                color = mred,
                fontSize = 20.sp
            ), modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = about,
            style = TextStyle(
                fontFamily = mFont.fsmedium,
                fontWeight = FontWeight.Medium,
                color = lightBlack,
                fontSize = 16.sp
            ), modifier = Modifier
                .fillMaxWidth()
        )
    }
}