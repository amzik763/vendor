package com.pampiway.vendor.utility

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pampiway.vendor.ui.theme.lightGrey
import com.pampiway.vendor.ui.theme.mgreen
import com.pampiway.vendor.ui.theme.mred

@Composable
fun Switch2(
    switchON: Boolean, // Receive the state as a parameter
    onToggleChange: (Boolean) -> Unit, // Function to change the state
    scale: Float = 1f,
    width: Dp = 40.dp,
    height: Dp = 24.dp,
    gapBetweenThumbAndTrackEdge: Dp = 2.dp,
) {
    val thumbRadius = (height / 2) - gapBetweenThumbAndTrackEdge

    // To move thumb, we need to calculate the position (along x axis)
    val animatePosition = animateFloatAsState(
        targetValue = if (switchON)
            with(LocalDensity.current) { (width - thumbRadius - gapBetweenThumbAndTrackEdge).toPx() }
        else
            with(LocalDensity.current) { (thumbRadius + gapBetweenThumbAndTrackEdge).toPx() }
    )

    Canvas(
        modifier = Modifier
            .size(width = width, height = height)
            .scale(scale = scale)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        // Call the parent function to update the state when the user taps the switch
                        showLogs("TOGGLE: ","Changed + $switchON")
                        if(switchON)
                        onToggleChange(false)
                        else
                        onToggleChange(true)// Toggle the state
                    }
                )
            }
    ) {
        // Track (no stroke)
        drawRoundRect(
            color = if (switchON) mgreen else mred, // Blue when ON, Gray when OFF
            cornerRadius = CornerRadius(x = 12.dp.toPx(), y = 12.dp.toPx())  // Rounded corners
        )

        // Thumb
        drawCircle(
            color = Color.White,  // Thumb color
            radius = thumbRadius.toPx(),
            center = Offset(
                x = animatePosition.value,
                y = size.height / 2
            )
        )
    }


    // Display the text for ON or OFF
//    Text(text = if (switchON) "ON" else "OFF", fontSize = 14.sp)
}
