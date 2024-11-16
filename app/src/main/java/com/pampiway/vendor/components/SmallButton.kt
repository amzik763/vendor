package com.pampiway.vendor.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import com.pampiway.vendor.ui.theme.mred
import com.pampiway.vendor.utility.mFont

@Composable
fun SmallButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 5.dp,
    backgroundColor: Color = mred,
    contentColor: Color = Color.White,
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(cornerRadius),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ),
        contentPadding = PaddingValues(0.dp),

        modifier = modifier
            .fillMaxWidth().height(45.dp)
    ) {
        Text(text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = mFont.fsmedium,
            color = Color.White,
            modifier = Modifier
                .padding(horizontal = 8.dp))
    }
}

@Composable
fun SmallButtonBorder(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 5.dp,
    backgroundColor: Color = Color.White, // Transparent background for border effect
    borderColor: Color = mred, // Border color
    contentColor: Color = mred, // Content color
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(cornerRadius),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ),
        contentPadding = PaddingValues(0.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(45.dp)
            .border(
                BorderStroke(2.dp, borderColor), // Add border with specified color
                shape = RoundedCornerShape(cornerRadius)
            )
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = mFont.fsmedium,
            color = contentColor, // Match text color with contentColor
            modifier = Modifier
                .padding(horizontal = 8.dp)
        )
    }
}
