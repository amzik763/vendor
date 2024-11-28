package com.pampiway.vendor.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.pampiway.vendor.ui.theme.lightBlack
import com.pampiway.vendor.ui.theme.mred
import com.pampiway.vendor.utility.mFont
import com.pampiway.vendor.utility.myComponent
import com.pampiway.vendor.utility.myComponent.navController
import com.pampiway.vendor.utility.myComponent.registerViewModel
import kotlinx.coroutines.launch
@Composable
fun EditProfile() {
    Column(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxSize()
            .background(color = Color.White)
    ) {
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
                            text = "Edit Profile",
                            style = TextStyle(
                                fontFamily = mFont.fsbold,
                                fontWeight = FontWeight.Bold,
                                color = lightBlack,
                                fontSize = 26.sp
                            ),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                    CustomButton()
                }

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
                    text = "D.O.B",
                    value = dob,
                    onValueChange = { dob = it },
                    keyboardType = KeyboardType.Text,
                    errorMessage = if (validationTriggered && dob.isEmpty()) "Date of Birth cannot be empty" else null
                )

                Spacer(modifier = Modifier.height(36.dp))
            }

            // Bottom button
            SmallButtonBorder(
                onClick = {
                    validationTriggered = true
                    Log.d("InputScreen", "Name: $name")
                    Log.d("InputScreen", "Phone Number: $phoneNumber")
                    Log.d("InputScreen", "Email: $email")
                    Log.d("InputScreen", "Dob: $dob")
                    val errors = mutableMapOf<String, String>()
                    if (name.isEmpty()) errors["name"] = "Name cannot be empty"
                    if (phoneNumber.length != 10) errors["phone"] = "Phone number must be 10 digits"
                    if (!isValidEmail(email)) errors["email"] = "Invalid email address"
                    if (dob.isEmpty()) errors["dob"] = "Date of Birth cannot be empty"
                    if (errors.isEmpty()) {
                        Log.d("InputScreen", "Validated inputs")
                        scope.launch {
                            // Add your update logic here
                        }
                    } else {
                        Log.d("InputScreen", "Errors: $errors")
                    }
                },
                text = "Update Profile"
            )
        }
    }
}


@Preview
@Composable
fun CustomButton() {
    Box(
        modifier = Modifier
            .height(28.dp) // Set width and height
            .background(color = Color.White, shape = RoundedCornerShape(50)).border(1.dp, mred, RoundedCornerShape(50)), // Padding
    ) {
        Row(modifier = Modifier.height(28.dp).padding(horizontal = 8.dp).align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center, // Center elements
        ) {
            // Element A: Image
            Image(
                painter = painterResource(id = R.drawable.ic_history), // Replace with your image resource
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


fun isValidEmail2(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}