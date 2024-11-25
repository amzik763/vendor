package com.pampiway.vendor.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.tooling.preview.Preview
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
import com.pampiway.vendor.ui.theme.mgreen
import com.pampiway.vendor.ui.theme.mgreenlight
import com.pampiway.vendor.ui.theme.mred
import com.pampiway.vendor.ui.theme.mredlight
import com.pampiway.vendor.utility.mFont
import com.pampiway.vendor.utility.myComponent
import com.pampiway.vendor.utility.myComponent.navController


data class HistoryItem(
    val orderNo: Int,
    val date: String,
    val price: String,
    val start: String,
    val end: String,
    val status: String
)

@Composable
fun History(navController: NavHostController) {

    val historyItems = listOf(
        HistoryItem(
            orderNo = 101,
            date = "2024-11-01",
            price = "₹450",
            start = "Connaught Place, New Delhi, India",
            end = "Dilli Haat, INA, New Delhi, India",
            status = "Completed"
        ),
        HistoryItem(
            orderNo = 102,
            date = "2024-11-15",
            price = "₹300",
            start = "Bandra West, Mumbai, India",
            end = "Marine Drive, Mumbai, India",
            status = "Pending"
        ),
        HistoryItem(
            orderNo = 103,
            date = "2024-11-20",
            price = "₹150",
            start = "Electronic City, Bengaluru, India",
            end = "Whitefield, Bengaluru, India",
            status = "Cancelled"
        )
    )



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(vertical = 16.dp, horizontal = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painterResource(id = R.drawable.ic_arrow),
                contentDescription = "back",
                modifier = Modifier.size(32.dp).clickable {
                    myComponent.navController.popBackStack()
                },
                contentScale = ContentScale.Fit
            )
            Text(
                text = "History",
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

        HistoryList(history = historyItems)

    }


}

@Composable
fun HistoryList(history: List<HistoryItem>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(history) { historyItem ->
            HistoryItemContent(item = historyItem)
            Spacer(modifier = Modifier.height(8.dp)) // Space between items
        }
    }
}


@Composable
fun HistoryItemContent(item: HistoryItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.5.dp,
                color = mred,
                shape = RoundedCornerShape(12.dp)
            ),
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Dynamic Status
            Text(
                text = item.status,
                style = TextStyle(
                    fontFamily = mFont.fsmedium,
                    fontSize = 14.sp,
                    color = if (item.status == "Completed") mgreen else mred
                ),
                modifier = Modifier
                    .background(
                        color = if (item.status == "Completed") mgreenlight else mredlight,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(vertical = 4.dp, horizontal = 12.dp)
            )

            // Dynamic Order Number
            Text(
                text = "Order: #${item.orderNo}",
                textAlign = TextAlign.End,
                style = TextStyle(
                    fontFamily = mFont.fsbold,
                    fontSize = 18.sp,
                    color = mred
                )
            )
        }

        Row {
            ColumnWithCenteredCircles()
            ColumnWithText(start = item.start, end = item.end)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Dynamic Price
            Text(
                text = item.price,
                style = TextStyle(
                    fontFamily = mFont.fsmedium,
                    fontSize = 18.sp,
                    color = mred
                ),
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
            )

            // Dynamic Date
            Text(
                text = item.date,
                textAlign = TextAlign.End,
                style = TextStyle(
                    fontFamily = mFont.fsregular,
                    fontSize = 16.sp,
                    color = darkGrey
                ), modifier = Modifier.padding(end = 4.dp)
            )
        }
    }
}

@Composable
fun ColumnWithText(start: String, end: String) {
    Column(
        modifier = Modifier
            .padding(start = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
            .wrapContentHeight(), // Wrap height to content
        horizontalAlignment = Alignment.Start // Align items to the start horizontally
    ) {
        Text(
            text = start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp, top = 2.dp)
                .height(65.dp), // Align text with line
            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium, fontFamily = mFont.fsmedium)
        )

        Text(
            text = end,
            modifier = Modifier.fillMaxWidth().padding(end = 8.dp, top = 2.dp),
            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
        )
    }
}
@Preview
@Composable
fun CenteredCircles(mode: Int = 1) {
    // Define colors based on the mode
    val borderColor = when (mode) {
        0 -> lightGrey
        1 -> mred
        2 -> mgreen
        else -> mred // Default color
    }

    val backgroundColorB = when (mode) {
        0 -> Color.White
        1 -> mred
        2 -> mgreen
        else -> mred // Default color
    }

    Box(
        contentAlignment = Alignment.Center, // Centers content inside the box
        modifier = Modifier.size(25.dp) // Ensures enough space for circles
    ) {
        // Circle A
        Box(
            modifier = Modifier
                .size(25.dp) // Size of Circle A
                .border(
                    width = 2.dp,
                    color = borderColor,
                    shape = CircleShape
                )
                .background(
                    color = Color.White,
                    shape = CircleShape
                )
        )

        // Circle B
        Box(
            modifier = Modifier
                .size(16.dp) // Size of Circle B
                .background(
                    color = backgroundColorB,
                    shape = CircleShape
                )
        )
    }
}

@Composable
fun ColumnWithCenteredCircles() {
    Column(
        modifier = Modifier
            .padding(start = 8.dp, bottom = 8.dp)
            .width(40.dp)
            .wrapContentHeight(), // Wrap height to content
        horizontalAlignment = Alignment.Start
        // Center items horizontally
    ) {
        // Circle A
        CenteredCircles(mode = 1)

        // Vertical line
        Box(
            modifier = Modifier
                .padding(start = 12.dp)
                .width(2.dp) // Line width
                .height(40.dp) // Adjust height as per the gap you want between circles
                .background(Color.Gray)
        )

        // Circle B
        CenteredCircles(mode = 2)
    }
}
