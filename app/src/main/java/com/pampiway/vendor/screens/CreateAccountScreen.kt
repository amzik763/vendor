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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pampiway.vendor.components.SmallButton
import com.pampiway.vendor.components.SmallButtonBorder
import com.pampiway.vendor.inputComponent
import com.pampiway.vendor.ui.theme.mred
import com.pampiway.vendor.utility.mFont
import com.pampiway.vendor.utility.myComponent
import com.pampiway.vendor.utility.myComponent.navController
import com.pampiway.vendor.utility.myComponent.registerViewModel
import kotlinx.coroutines.launch

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
        var validationTriggered by remember { mutableStateOf(false) }
        var name by remember { mutableStateOf("") }
        var phoneNumber by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var city by remember { mutableStateOf("") }
        var district by remember { mutableStateOf("") }
        var state by remember { mutableStateOf("") }
        var pincode by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }

        val scope = rememberCoroutineScope()

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




        inputComponent(
            text = "Name",
            value = name,
            onValueChange = { name = it },
            keyboardType = KeyboardType.Text,
            errorMessage = if (validationTriggered && name.isEmpty()) "Name cannot be empty" else null
        )

        inputComponent(
            text = "Phone Number",
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            keyboardType = KeyboardType.Number,
            errorMessage = if (validationTriggered && phoneNumber.length != 10) "Phone number must be 10 digits" else null
        )

        inputComponent(
            text = "Email",
            value = email,
            onValueChange = { email = it },
            keyboardType = KeyboardType.Email,
            errorMessage = if (validationTriggered && !isValidEmail(email)) "Invalid email address" else null
        )

        inputComponent(
            text = "City",
            value = city,
            onValueChange = { city = it },
            keyboardType = KeyboardType.Text,
            errorMessage = if (validationTriggered && city.isEmpty()) "City cannot be empty" else null
        )

        inputComponent(
            text = "District",
            value = district,
            onValueChange = { district = it },
            keyboardType = KeyboardType.Text,
            errorMessage = if (validationTriggered && district.isEmpty()) "District cannot be empty" else null
        )

        inputComponent(
            text = "State",
            value = state,
            onValueChange = { state = it },
            keyboardType = KeyboardType.Text,
            errorMessage = if (validationTriggered && state.isEmpty()) "State cannot be empty" else null
        )

        inputComponent(
            text = "Pincode",
            value = pincode,
            onValueChange = { pincode = it },
            keyboardType = KeyboardType.Number,
            errorMessage = if (validationTriggered && pincode.isEmpty()) "Pincode cannot be empty" else null
        )

        inputComponent(
            text = "Password",
            value = password,
            onValueChange = { password = it },
            keyboardType = KeyboardType.Password,
            errorMessage = if (validationTriggered && password.length < 8) "Password must be at least 8 characters" else null
        )

        inputComponent(
            text = "Confirm Password",
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            keyboardType = KeyboardType.Password,
            errorMessage = if (validationTriggered && password != confirmPassword) "Passwords do not match" else null
        )

        Spacer(modifier = Modifier.height(36.dp))


        SmallButton(onClick = {
            validationTriggered = true
            Log.d("InputScreen", "Name: $name")
            Log.d("InputScreen", "Phone Number: $phoneNumber")
            Log.d("InputScreen", "Email: $email")
            Log.d("InputScreen", "City: $city")
            Log.d("InputScreen", "District: $district")
            Log.d("InputScreen", "State: $state")
            Log.d("InputScreen", "Pincode: $pincode")
            Log.d("InputScreen", "Password: $password")
            Log.d("InputScreen", "Confirm Password: $confirmPassword")

            val errors = mutableMapOf<String, String>()

            if (name.isEmpty()) errors["name"] = "Name cannot be empty"
            if (phoneNumber.length != 10) errors["phone"] = "Phone number must be 10 digits"
            if (!isValidEmail(email)) errors["email"] = "Invalid email address"
            if (city.isEmpty()) errors["city"] = "City cannot be empty"
            if (district.isEmpty()) errors["district"] = "District cannot be empty"
            if (state.isEmpty()) errors["state"] = "State cannot be empty"
            if (pincode.isEmpty()) errors["pincode"] = "Pincode cannot be empty"
            if (password.length < 8) errors["password"] = "Password must be at least 8 characters"
            if (password != confirmPassword) errors["confirmPassword"] = "Passwords do not match"

            if (errors.isEmpty()) {
                Log.d("InputScreen", "Validated inputs")
                scope.launch {
                    val isSuccessFul = registerViewModel.createAccount(
                        name, phoneNumber, email, city, district, state, pincode, password, confirmPassword
                    )
                    if(isSuccessFul){
                        navController.navigate("Home"){
                            popUpTo(0){
                                inclusive = true
                            }
                        }
                    }else{

                    }

                }
            } else {

                registerViewModel.errors = errors
                Log.d("InputScreen", "Errors: $errors")
//                myComponent.registerViewModel.showErrorDialog()
            }

//            registerViewModel.createAccount(name, phoneNumber, email, city, district, state, pincode, password, confirmPassword)
//            navController.navigate("Home")

        }, text = "Create")

        Spacer(modifier = Modifier.height(6.dp))

        SmallButtonBorder(onClick = {
            navController.popBackStack()
        }, text = "Already have an account")
    }
}

fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}