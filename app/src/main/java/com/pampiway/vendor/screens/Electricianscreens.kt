package com.pampiway.vendor.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.pampiway.vendor.R
import com.pampiway.vendor.ui.theme.darkGrey
import com.pampiway.vendor.ui.theme.lightBlack
import com.pampiway.vendor.ui.theme.medGrey
import com.pampiway.vendor.utility.mFont
import com.pampiway.vendor.utility.myComponent.navController


data class ElectricianItem(
    val imageRes: Int,
    val title: String,
    val subtitle: String
)

@Composable
fun fruitandshopScreen(navController: NavHostController) {

    var travelQuantity = remember {
        mutableStateOf(24)
    }

    val ElectriciansList = listOf(
        ElectricianItem(
            imageRes = R.drawable.ic_electrician,
            title = "Electric Service",
            subtitle = "all india service available"
        ),
        ElectricianItem(
            imageRes = R.drawable.ic_electrician,
            title = "Plumber Service",
            subtitle = "all services available"
        ),
        ElectricianItem(
            imageRes = R.drawable.ic_electrician,
            title = "Service",
            subtitle = "Localized service available"
        )
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 12.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Item A: Image and Text
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow),
                    contentDescription = "back",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            navController.popBackStack()
                        },
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = "Electrician & Plumber",
                    style = TextStyle(
                        fontFamily = mFont.fsbold,
                        fontWeight = FontWeight.Bold,
                        color = lightBlack,
                        fontSize = 23.sp
                    ),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            // Item B: Image
            Image(
                painter = painterResource(id = R.drawable.ic_searchtop),
                contentDescription = "other",
                modifier = Modifier.size(32.dp),
                contentScale = ContentScale.Fit
            )
        }

        Divider(
            color = Color.White,              // Set the color of the divider
            thickness = 4.dp,                // Set the thickness of the divider
            modifier = Modifier.fillMaxWidth() // Make the divider take full width
        )


        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){

            Text(text = "All Store",
                style = TextStyle(
                    fontFamily = mFont.fsbold,
                    fontWeight = FontWeight.Bold,
                    color = lightBlack,
                    fontSize = 20.sp
                ), modifier = Modifier.padding(start = 8.dp)
            )

            Text(text = "${travelQuantity.value} stores",
                style = TextStyle(
                    fontFamily = mFont.fsregular,
                    fontWeight = FontWeight.Normal,
                    color = medGrey,
                    fontSize = 17.sp
                ), modifier = Modifier.padding(start = 8.dp)
            )

        }
        Spacer(modifier = Modifier.height(12.dp))

        ElectricianList(ElectricianList = ElectriciansList)

    }


}

@Composable
fun ElectricianList(ElectricianList: List<ElectricianItem>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(ElectricianList) { notification ->
            ElectricianContent(
                imageRes = notification.imageRes,
                title = notification.title,
                subtitle = notification.subtitle
            )
        }
    }
}

@Composable
fun ElectricianContent(
    imageRes: Int,
    title: String,
    subtitle: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
//            .background(mgreyish, shape = RoundedCornerShape(8.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            // Image on the left
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Electrician Image",
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
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
                        fontFamily = mFont.fsmedium,
                        color = lightBlack,
                        fontSize = 19.sp
                    )
                )
                Spacer(modifier = Modifier.height(2.dp)) // Space between title and subtitle
                Text(modifier = Modifier.padding(bottom = 10.dp),
                    text = subtitle,
                    style = TextStyle(
                        fontFamily = mFont.fsregular,
                        color = darkGrey,
                        fontSize = 14.sp
                    )
                )
            }
        }
    }
}
