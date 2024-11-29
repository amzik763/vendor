package com.pampiway.vendor.components

import android.view.Display
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pampiway.vendor.R
import com.pampiway.vendor.ui.theme.cardgrey
import com.pampiway.vendor.ui.theme.darkGrey
import com.pampiway.vendor.ui.theme.lightBlack
import com.pampiway.vendor.ui.theme.lightOrange
import com.pampiway.vendor.ui.theme.medGrey
import com.pampiway.vendor.ui.theme.mgreen
import com.pampiway.vendor.ui.theme.mred
import com.pampiway.vendor.utility.mFont

@Preview
@Composable
fun SmallComponents() {
    Column {

        StatusRow(progress = 50f)
        StatusCard()
        Spacer(modifier = Modifier.height(12.dp))
        LookingForRide()
        Spacer(modifier = Modifier.height(12.dp))
        DeliveryCharge()
    }

}




//COMPONENT D
@Preview
@Composable
fun DeliveryCharge() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
             // Padding around the column
    ) {

        Box(modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()
            .height(45.dp)
            .background(mred)){
            Text(text = "  Delivery Charge", style = TextStyle(
                fontFamily = mFont.fssemibold,
                fontSize = 16.sp,
                color = lightBlack
            ), modifier = Modifier.align(Alignment.CenterStart).padding(start = 40.dp))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { println("Favourites clicked!") }
                .padding(vertical = 8.dp, horizontal = 8.dp), // Space between rows
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_couponsticker),

                    contentDescription = "Favourites",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Favourites",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = mFont.fsmedium,
                    color = lightBlack,
                    modifier = Modifier
                        .padding(horizontal = 8.dp))
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_arrowforwardsmall),
                contentDescription = "Arrow Icon",
                modifier = Modifier.size(24.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { println("Address clicked!") }
                .padding(vertical = 8.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_cashsticker),

                    contentDescription = "Address",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Address",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = mFont.fsmedium,
                    color = lightBlack,
                    modifier = Modifier
                        .padding(horizontal = 8.dp))
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_arrowforwardsmall),
                contentDescription = "Arrow Icon",
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.padding(horizontal = 12.dp)){
            SmallButton(onClick = {

            }, text = "Continue" )
            Spacer(modifier = Modifier.height(12.dp))
        }


    }
}







//COMPONENT C
@Composable
fun LookingForRide() {
    // Remember progress state for interaction
    var progress by remember { mutableStateOf(3) }


    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "  Looking for Your", style = TextStyle(
            fontFamily = mFont.fsregular,
            fontSize = 12.sp,
            color = lightBlack
        ))
        Spacer(modifier = Modifier.height(2.dp))

        Text(text = "  Parcel Ride", style = TextStyle(
            fontFamily = mFont.fssemibold,
            fontSize = 15.sp,
            color = lightBlack
        ))
        Spacer(modifier = Modifier.height(4.dp))

        SyncedProgressBars(progress = progress)
        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)){
            Image(modifier = Modifier.align(Alignment.Center), painter = painterResource(id = R.drawable.ic_searchingride) , contentDescription = "search Image", contentScale = ContentScale.Fit )
        }

    }


}

@Composable
fun SyncedProgressBars(progress: Int) {
    val totalBars = 4

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(12.dp)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Loop to create progress bars
        repeat(totalBars) { index ->
            val barProgress = when {
                progress > index * 2 + 1 -> 100f // Full progress for this bar
                progress == index * 2 + 1 -> 50f // Half progress for this bar
                else -> 0f // No progress for this bar
            }

            LinearRoundedProgressBarWithIndicator(
                progress = barProgress,
                modifier = Modifier
                    .weight(1f)
                    .height(5.dp) // Set height of the progress bar
            )
        }
    }
}

@Composable
fun LinearRoundedProgressBarWithIndicator(progress: Float, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(
                cardgrey,
                shape = RoundedCornerShape(16.dp)
            ) // Background for the progress bar
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(fraction = progress / 100) // Progress as fraction of 1
                .background(mred, shape = RoundedCornerShape(16.dp)) // Progress indicator
        )
    }
}




//COMPONENT B
@Composable
fun StatusCard(pin: String = "3445") {

    var company by remember { mutableStateOf("RJ782 SS 02") }
    var vehicle by remember { mutableStateOf("HF DELUXE") }
    var name by remember { mutableStateOf("Kartik") }
    var rating by remember { mutableStateOf("4.5") }


    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Text(
            text = "Your Captain Is On His Way", style = TextStyle(
                fontFamily = mFont.fsmedium,
                fontSize = 16.sp,
                color = lightBlack
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(lightBlack)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "  Send your order with pin", style = TextStyle(
                    fontFamily = mFont.fsregular,
                    fontSize = 13.sp,
                    color = lightBlack
                )
            )
            Row( // Optional padding
                horizontalArrangement = Arrangement.spacedBy(8.dp), // Space between BoxPins
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Iterate through each character in the pin string
                pin.forEach { digit ->
                    BoxPin(text = digit.toString()) // Call BoxPin with each character
                }
            }

        }
        Spacer(modifier = Modifier.height(12.dp))
        RowCard(
            company = company,
            vehicle = vehicle,
            imageResIdA = R.drawable.ic_calloutlined,
            name = name,
            imageResIdB = R.drawable.ic_star,
            rating = rating
        )



    }

}

@Composable
fun BoxPin(text: String) {
    // Box UI
    Box(
        modifier = Modifier
            .size(24.dp) // Box size
            .background(color = lightBlack), // Always black background
        contentAlignment = Alignment.Center // Center content
    ) {
        // Text inside the box
        Text(
            text = text,
            style = TextStyle(
                fontSize = 12.sp, // Adjust text size as needed
                fontWeight = FontWeight.Medium,
                color = Color.White // White text color for contrast
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun RowCard(
    company: String,
    vehicle: String,
    imageResIdA: Int,
    name: String,
    imageResIdB: Int,
    rating: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = cardgrey, shape = RoundedCornerShape(8.dp))
            .padding(8.dp),
    ) {
        Row(
            modifier = Modifier
                .background(cardgrey)
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            // Element A: Column with title, subtitle, and image
            Column(
                modifier = Modifier.weight(1f), // Allocate weight for balanced spacing
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = company,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
                Text(
                    text = vehicle,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                )
                Image(
                    painter = painterResource(id = imageResIdA),
                    contentDescription = "Call",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {

                        }
                )
            }

            // Element B: Column with text and a row containing an image and text
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = name,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Image(
                        painter = painterResource(id = imageResIdB),
                        contentDescription = "Image B",
                        modifier = Modifier.size(16.dp),
                        colorFilter = ColorFilter.tint(lightOrange)// Smaller size for this image
                    )
                    Text(
                        text = rating,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray
                        )
                    )
                }
            }
        }
    }
}




//COMPONENT A
@Composable
fun StatusRow(progress: Float) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        // Element A: StatusBox on the left
        StatusBoxWithText(status = 1, "Pick")


        // Element B: Linear Progress Bar
        LinearProgressBarWithIndicator(
            progress = progress,
            modifier = Modifier
                .width(150.dp)
                .height(4.dp) // Adjust thickness
        )


        // Element C: StatusBox on the right
        StatusBoxWithText(status = 0, "Pick Up")
    }
}

@Composable
fun LinearProgressBarWithIndicator(progress: Float, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(medGrey) // Background for the progress bar
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(fraction = progress / 100) // Progress as fraction of 1
                .background(mgreen) // Progress indicator
        )
    }
}

@Composable
fun StatusBoxWithText(status: Int, text: String) {
    val backgroundColor: Color
    val imageResId: Int?

    // Determine background color and image based on status
    when (status) {
        0 -> {
            backgroundColor = medGrey
            imageResId = null // No image
        }

        1 -> {
            backgroundColor = mgreen
            imageResId = R.drawable.ic_checkwhite // Replace with your green check image
        }

        2 -> {
            backgroundColor = mred
            imageResId = R.drawable.ic_close // Replace with your red close image
        }

        else -> {
            backgroundColor = medGrey // Default fallback
            imageResId = null
        }
    }

    // Column wrapping Box and Text
    Column(
        horizontalAlignment = Alignment.Start // Center horizontally
    ) {
        // Box UI
        Spacer(modifier = Modifier.height(19.dp))
        Box(
            modifier = Modifier
                .size(30.dp)
                .background(color = backgroundColor), // Dynamic background color
            contentAlignment = Alignment.Center // Center content
        ) {
            imageResId?.let { // Add image only if it's not null
                Image(
                    painter = painterResource(id = it),
                    contentDescription = "Status Image",
                    modifier = Modifier.size(18.dp) // Adjust image size
                )
            }
        }

        // Text below the box
        Spacer(modifier = Modifier.height(4.dp)) // Space between box and text
        Text(
            modifier = Modifier.height(15.dp),
            text = text,
            style = TextStyle(
                fontSize = 12.sp,
                color = darkGrey,
                fontWeight = FontWeight.Normal
            ),
            textAlign = TextAlign.Center
        )
    }
}


