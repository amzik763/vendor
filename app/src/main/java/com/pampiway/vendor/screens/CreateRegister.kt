package com.pampiway.vendor.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pampiway.vendor.R
import com.pampiway.vendor.inputText
import com.pampiway.vendor.ui.theme.VendorTheme
import com.pampiway.vendor.ui.theme.lightBlack
import com.pampiway.vendor.ui.theme.mred
import com.pampiway.vendor.utility.mFont

@Preview(showBackground = true)
@Composable
fun CreateRegister() {
    VendorTheme {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(vertical = 16.dp, horizontal = 12.dp)
        ){
            Text(text = "Let's Login",
                style = TextStyle(
                    fontFamily = mFont.fsbold,
                    fontWeight = FontWeight.Bold,
                    color = lightBlack,
                    fontSize = 26.sp
                ), modifier = Modifier.padding(top = 36.dp)
            )

            Row(modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
                .height(150.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center){

                Image(
                    painterResource(id = R.drawable.logopampi),
                    contentDescription = "logo",
                    modifier = Modifier.size(150.dp),
                    contentScale = ContentScale.Fit)

            }

            Divider(
                color = Color.White,              // Set the color of the divider
                thickness = 4.dp,                // Set the thickness of the divider
                modifier = Modifier.fillMaxWidth() // Make the divider take full width
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Divider(
                    color = mred, // Set the color of the divider
                    thickness = 4.dp, // Set the thickness of the divider
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center) // Center the divider vertically within the Box
                )
                Text(
                    text = "DRIVER",
                    style = TextStyle(
                        fontFamily = mFont.fsbold,
                        fontWeight = FontWeight.Bold,
                        color = mred,
                        fontSize = 26.sp
                    ),
                    modifier = Modifier
                        .align(Alignment.Center) // Center the text within the Box
                        .background(Color.White)
                        .padding(8.dp) // Optional padding
                )


            }


            Spacer(modifier = Modifier.height(24.dp))

            inputText(text = "abc")

        }

    }
}
