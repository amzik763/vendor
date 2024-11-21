package com.pampiway.vendor.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pampiway.vendor.components.SmallButton
import com.pampiway.vendor.inputComponent
import com.pampiway.vendor.ui.theme.mred
import com.pampiway.vendor.utility.mFont
import com.pampiway.vendor.utility.myComponent.navController

@Composable
fun CreateAccountScreen() {
    Column(
        modifier = Modifier
            .padding(top = 32.dp)
            .fillMaxSize()
            .background(color = Color.White)
            .verticalScroll(rememberScrollState()) // Enable vertical scrolling
            .padding(vertical = 16.dp, horizontal = 12.dp)
    ) {
        Text(
            text = "Create Account",
            style = TextStyle(
                fontFamily = mFont.fsbold,
                fontWeight = FontWeight.Bold,
                color = mred,
                fontSize = 26.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(36.dp))

        inputComponent(text = "Name")
        inputComponent(text = "Phone Number")
        inputComponent(text = "Email")
        inputComponent(text = "City")
        inputComponent(text = "District")
        inputComponent(text = "State")
        inputComponent(text = "Pincode")
        inputComponent(text = "Password")
        inputComponent(text = "Confirm Password")
        Spacer(modifier = Modifier.height(36.dp))
        SmallButton(onClick = {
            navController.navigate("dashboard")
        }, text = "Create")
    }
}
