package com.pampiway.vendor.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pampiway.vendor.ui.theme.darkGrey
import com.pampiway.vendor.ui.theme.lightOrange
import com.pampiway.vendor.ui.theme.mred

@Composable
fun InputText(
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
