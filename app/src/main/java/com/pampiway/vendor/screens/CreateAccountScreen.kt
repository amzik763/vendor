package com.pampiway.vendor.screens

import android.util.Log
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.pampiway.vendor.utility.myComponent.registerViewModel

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

        var name by remember { mutableStateOf("") }
        var phoneNumber by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var city by remember { mutableStateOf("") }
        var district by remember { mutableStateOf("") }
        var state by remember { mutableStateOf("") }
        var pincode by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }

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

        inputComponent(text = "Name", value = name, onValueChange = { name = it })
        inputComponent(text = "Phone Number", value = phoneNumber, onValueChange = { phoneNumber = it })
        inputComponent(text = "Email", value = email, onValueChange = { email = it })
        inputComponent(text = "City", value = city, onValueChange = { city = it })
        inputComponent(text = "District", value = district, onValueChange = { district = it })
        inputComponent(text = "State", value = state, onValueChange = { state = it })
        inputComponent(text = "Pincode", value = pincode, onValueChange = { pincode = it })
        inputComponent(text = "Password", value = password, onValueChange = { password = it })
        inputComponent(text = "Confirm Password", value = confirmPassword, onValueChange = { confirmPassword = it })

        Spacer(modifier = Modifier.height(36.dp))
        SmallButton(onClick = {

            Log.d("InputScreen", "Name: $name")
            Log.d("InputScreen", "Phone Number: $phoneNumber")
            Log.d("InputScreen", "Email: $email")
            Log.d("InputScreen", "City: $city")
            Log.d("InputScreen", "District: $district")
            Log.d("InputScreen", "State: $state")
            Log.d("InputScreen", "Pincode: $pincode")
            Log.d("InputScreen", "Password: $password")
            Log.d("InputScreen", "Confirm Password: $confirmPassword")
            registerViewModel.createAccount(name, phoneNumber, email, city, district, state, pincode, password, confirmPassword)
            navController.navigate("dashboard")

        }, text = "Create")
    }
}
