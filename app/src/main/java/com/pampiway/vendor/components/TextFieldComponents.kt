package com.pampiway.vendor.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pampiway.vendor.R
import com.pampiway.vendor.ui.theme.darkGrey
import com.pampiway.vendor.ui.theme.lightBlack
import com.pampiway.vendor.ui.theme.lightOrange
import com.pampiway.vendor.ui.theme.mblue
import com.pampiway.vendor.ui.theme.medGrey
import com.pampiway.vendor.ui.theme.mred
import com.pampiway.vendor.utility.mFont
import com.pampiway.vendor.utility.myComponent.navController


@Composable
fun InputText(
    modifier: Modifier = Modifier,
    text: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {},
    color: Color,
    maxLength: Int,
    keyboardOptions: KeyboardOptions
) {
    var isFocused by remember { mutableStateOf(false) }
    val borderColor = if (isFocused) mred else medGrey // Conditional border color

    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = modifier
            .border(1.dp, borderColor, RoundedCornerShape(6.dp)) // Custom border
            .padding(horizontal = 4.dp, vertical = 4.dp) // Reduce padding between text and border
            .height(36.dp) // Set height of the text field
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            // Text Input
            BasicTextField(
                value = text,
                onValueChange = {
                    val newText = it.take(maxLength)
                    onTextChange(newText)
                },
                maxLines = maxLine,
                keyboardOptions = keyboardOptions,
                keyboardActions = KeyboardActions(onDone = {
                    onImeAction()
                    keyboardController?.hide()
                }),
                textStyle = LocalTextStyle.current.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = color,
                    fontSize = 14.sp
                ),
                decorationBox = { innerTextField ->
                    // Optional: Add placeholder or any additional decoration
                    innerTextField()
                },
                modifier = Modifier
                    .weight(1f) // Take up available space
                    .padding(start = 8.dp)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused // Update focus state
                    }
            )

            // Clear Icon
            if (text.isNotEmpty() && isFocused) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_close2), // Replace with your icon resource
                    contentDescription = "Clear text",
                    modifier = Modifier.padding(end = 4.dp)
                        .size(16.dp)
                        .clickable {
                            onTextChange("") // Clear the text
                        },
                    tint = mred // Adjust icon color
                )
            }
        }
    }
}


@Composable
fun CInputText(
    modifier: Modifier = Modifier,
    text: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction:() -> Unit = {},
    color: Color,
    maxLength: Int,
    keyboardOptions: KeyboardOptions

) {


    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = mred,
            unfocusedBorderColor = darkGrey,
            focusedLabelColor = Color.DarkGray
        ),
        value = text,
        onValueChange = {val newText = it.take(maxLength)
            onTextChange(newText)},
        maxLines = maxLine,
        keyboardOptions = keyboardOptions,
        keyboardActions =  KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()

        }),
        modifier = Modifier.fillMaxWidth()
            .height(50.dp),
        textStyle = LocalTextStyle.current.copy(
            fontWeight = FontWeight.SemiBold,
            color = color,
            fontSize = 14.sp
        ),
        shape = RoundedCornerShape(6.dp)
    )
}

@Composable
fun InputTextLarge(
    modifier: Modifier = Modifier,
    text: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction:() -> Unit = {},
    color: Color,
    maxLength: Int,
    keyboardOptions: KeyboardOptions

) {


    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = mred,
            unfocusedBorderColor = darkGrey,
            focusedLabelColor = Color.DarkGray
        ),
        value = text,
        onValueChange = {val newText = it.take(250)
            onTextChange(newText)},
        maxLines = 5,
        keyboardOptions = keyboardOptions,
        keyboardActions =  KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()

        }),
        modifier = Modifier.fillMaxWidth()
            .height(150.dp),
        textStyle = LocalTextStyle.current.copy(
            fontWeight = FontWeight.SemiBold,
            color = color,
            fontSize = 14.sp
        ),
        shape = RoundedCornerShape(6.dp)
    )
}


@Composable
fun InputTextWithIconOld(
    modifier: Modifier = Modifier,
    text: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction:() -> Unit = {},
    color: Color,
    iconResId: Int,
    maxLength: Int,
    keyboardOptions: KeyboardOptions

) {


    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = mred,
            unfocusedBorderColor = darkGrey,
            focusedLabelColor = Color.DarkGray
        ),
        value = text,
        onValueChange = {val newText = it.take(maxLength)
            onTextChange(newText)},
        maxLines = maxLine,
        keyboardOptions = keyboardOptions,
        keyboardActions =  KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()

        }),
        modifier = Modifier.fillMaxWidth()
            .height(50.dp),
        textStyle = LocalTextStyle.current.copy(
            fontWeight = FontWeight.SemiBold,
            color = color,
            fontSize = 14.sp
        ),
        trailingIcon = {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = "Password Icon",
                modifier = Modifier
                    .size(19.dp)
                    .fillMaxWidth()
            )
        },
        shape = RoundedCornerShape(6.dp)
    )
}


@Composable
fun InputTextWithIcon(
    modifier: Modifier = Modifier,
    text: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {},
    color: Color,
    iconResIdVisible: Int,
    iconResIdHidden: Int,
    maxLength: Int,
    keyboardOptions: KeyboardOptions
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var passwordVisible by remember { mutableStateOf(false) } // State for visibility toggle

    OutlinedTextField(
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = mred,
            unfocusedBorderColor = darkGrey,
            focusedLabelColor = Color.DarkGray
        ),
        value = text,
        onValueChange = {
            val newText = it.take(maxLength)
            onTextChange(newText)
        },
        maxLines = maxLine,
        keyboardOptions = keyboardOptions.copy(
            keyboardType = if (passwordVisible) KeyboardType.Text else KeyboardType.Password
        ),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }),
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        textStyle = LocalTextStyle.current.copy(
            fontWeight = FontWeight.SemiBold,
            color = color,
            fontSize = 14.sp
        ),
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    painter = painterResource(
                        id = if (passwordVisible) iconResIdVisible else iconResIdHidden
                    ),
                    contentDescription = if (passwordVisible) "Hide Password" else "Show Password",
                    modifier = Modifier.size(19.dp)
                )
            }
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        shape = RoundedCornerShape(6.dp)
    )
}


@Composable
fun inputComponent(
    text: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    errorMessage: String? = null
) {
    Column {
        Text(
            text = text,
            style = TextStyle(
                fontFamily = mFont.fsregular,
                color = darkGrey,
                fontSize = 15.sp
            )
        )

        Spacer(modifier = Modifier.height(6.dp))

        InputText(
            modifier = Modifier
                .padding(top = 2.dp, bottom = 4.dp),
            text = value,
            color = Color.Black,
            maxLine = 1,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = keyboardType
            ),
            onTextChange = onValueChange,
            maxLength = if (keyboardType == KeyboardType.Number) 10 else Int.MAX_VALUE
        )

        if (!errorMessage.isNullOrEmpty()) {
            Text(
                text = errorMessage,
                style = TextStyle(
                    color = mred,
                    fontSize = 12.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(14.dp))
    }
}

@Composable
fun inputText(text: String) {

    var inputEmail by remember { mutableStateOf("") }
    var inputPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Text(text = "E-Mail",
        style = TextStyle(
            fontFamily = mFont.fsregular,
            color = darkGrey,
            fontSize = 18.sp
        )
    )

    Spacer(modifier = Modifier.height(8.dp))

    InputText(
        modifier = Modifier
            .padding(top = 2.dp, bottom = 4.dp),
        text = inputEmail,
        color = Color.Black,
        maxLine = 1,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email),
        onTextChange = { inputEmail = it },
        maxLength = 50
    )


    Spacer(modifier = Modifier.height(20.dp))

    Text(text = "Password",
        style = TextStyle(
            fontFamily = mFont.fsregular,
            color = darkGrey,
            fontSize = 18.sp
        )
    )

    Spacer(modifier = Modifier.height(8.dp))

    InputTextWithIcon(
        modifier = Modifier
            .padding(top = 2.dp, bottom = 4.dp),
        text = inputPassword,
        color = Color.Black,
        maxLine = 1,
        iconResIdVisible = R.drawable.ic_eyeopen, // Icon for visible password
        iconResIdHidden = R.drawable.ic_eyeclose, // Icon for hidden password
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        onTextChange = { inputPassword = it },
        onImeAction = { /* Handle Done action */ },
        maxLength = 50
    )

    Spacer(modifier = Modifier.height(4.dp))

    Text(
        text = "Forgot Password",
        style = TextStyle(
            fontFamily = mFont.fsregular,
            color = mblue,
            fontSize = 15.sp,
            textAlign = TextAlign.End // Align the text to the end
        ),
        modifier = Modifier.fillMaxWidth() // Make the Text take the maximum width
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = errorMessage,
        style = TextStyle(
            color = mred,
            fontSize = 14.sp,
            fontFamily = mFont.fsregular
        ),
        modifier = Modifier.fillMaxWidth()
    )


    Spacer(modifier = Modifier.height(48.dp))


    /* CenterSwipeButton2(
         textLeft = "Decline <<<",
         textRight = ">>> Accept",
         onSwipeLeft = {
             println("Swiped Left!")
         },
         onSwipeRight = {
             println("Swiped Right!")
         }
     )*/

    SmallButton(
        onClick = {
            when {
                inputEmail.isBlank() -> {
                    errorMessage = "Email cannot be empty"
                }
                !android.util.Patterns.EMAIL_ADDRESS.matcher(inputEmail).matches() -> {
                    errorMessage = "Invalid email address"
                }
                inputPassword.isBlank() -> {
                    errorMessage = "Password cannot be empty"
                }
                else -> {
                    errorMessage = ""
                    // Navigate to Home and clear the backstack
                    navController.navigate("Home") {
                        popUpTo(0) { inclusive = true }
                    }
                }
            }
        },
        text = "Submit"
    )

    Spacer(modifier = Modifier.height(12.dp))

    SmallButtonBorder(onClick = {
        navController.navigate("register")
    }, text = "Become A Delivery Partner")


}

