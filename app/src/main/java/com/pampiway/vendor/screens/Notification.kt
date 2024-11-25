package com.pampiway.vendor.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.pampiway.vendor.R
import com.pampiway.vendor.components.InputText
import com.pampiway.vendor.components.InputTextLarge
import com.pampiway.vendor.components.InputTextWithIcon
import com.pampiway.vendor.components.SmallButton
import com.pampiway.vendor.components.SmallButtonBorder
import com.pampiway.vendor.inputText
import com.pampiway.vendor.ui.theme.darkGrey
import com.pampiway.vendor.ui.theme.lightBlack
import com.pampiway.vendor.ui.theme.lightGrey
import com.pampiway.vendor.ui.theme.mblue
import com.pampiway.vendor.ui.theme.mred
import com.pampiway.vendor.utility.mFont
import com.pampiway.vendor.utility.myComponent.navController


data class NotificationItem(
    val imageRes: Int,
    val title: String,
    val subtitle: String
)

@Composable
fun Notification(navController: NavHostController) {

    val notifications = listOf(
        NotificationItem(
            imageRes = R.drawable.ic_reward,
            title = "You have a new offer: ₹75 flat on 10 order completions",
            subtitle = "Redeem your rewards"
        ),
        NotificationItem(
            imageRes = R.drawable.ic_reward,
            title = "Exclusive Discount: Get 20% off on your next order",
            subtitle = "Offer valid till tomorrow"
        ),
        NotificationItem(
            imageRes = R.drawable.ic_reward,
            title = "Congratulations! You've earned a bonus",
            subtitle = "Check your wallet for details"
        )
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(vertical = 16.dp, horizontal = 12.dp)
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start){
            Image(
                painterResource(id = R.drawable.ic_arrow),
                contentDescription = "back",
                modifier = Modifier.size(32.dp),
                contentScale = ContentScale.Fit)
            Text(text = "Notification",
                style = TextStyle(
                    fontFamily = mFont.fsbold,
                    fontWeight = FontWeight.Bold,
                    color = lightBlack,
                    fontSize = 26.sp
                ), modifier = Modifier.padding(start = 8.dp)
            )
        }
        Divider(
            color = Color.White,              // Set the color of the divider
            thickness = 4.dp,                // Set the thickness of the divider
            modifier = Modifier.fillMaxWidth() // Make the divider take full width
        )
        Spacer(modifier = Modifier.height(4.dp))

        NotificationList(notifications = notifications)

    }


}

@Composable
fun NotificationList(notifications: List<NotificationItem>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(notifications) { notification ->
            NotificationContent(
                imageRes = notification.imageRes,
                title = notification.title,
                subtitle = notification.subtitle
            )
        }
    }
}

@Composable
fun NotificationContent(
    imageRes: Int,
    title: String,
    subtitle: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                width = 1.5.dp,
                color = mred,
                shape = RoundedCornerShape(12.dp)
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            // Image on the left
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Notification Image",
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp)).clickable {
                        navController.popBackStack()
                    }
            )

            Spacer(modifier = Modifier.width(8.dp)) // Space between image and text

            // Title and subtitle on the right
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        fontFamily = mFont.fsregular,
                        color = darkGrey,
                        fontSize = 17.sp
                    )
                )
                Spacer(modifier = Modifier.height(4.dp)) // Space between title and subtitle
                Text(
                    text = subtitle,
                    style = TextStyle(
                        fontFamily = mFont.fsbold,
                        color = lightGrey,
                        fontSize = 14.sp
                    )
                )
            }
        }
    }
}
