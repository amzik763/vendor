package com.pampiway.vendor.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.pampiway.vendor.ui.theme.darkGrey
import com.pampiway.vendor.ui.theme.lightBlack
import com.pampiway.vendor.ui.theme.lightGrey
import com.pampiway.vendor.ui.theme.lightOrange
import com.pampiway.vendor.ui.theme.mblue
import com.pampiway.vendor.ui.theme.mred
import com.pampiway.vendor.utility.mFont
import com.pampiway.vendor.utility.myComponent
import com.pampiway.vendor.utility.myComponent.navController

@Composable
fun ratingScreen(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(vertical = 16.dp, horizontal = 12.dp)
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 0.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start){

            Image(
                painterResource(id = R.drawable.ic_arrow),
                contentDescription = "back",
                modifier = Modifier.size(32.dp).clickable {
                    myComponent.navController.popBackStack()
                },
                contentScale = ContentScale.Fit)

            Column(){
                Text(text = "Rating for Burger",
                    style = TextStyle(
                        fontFamily = mFont.fsbold,
                        fontWeight = FontWeight.Bold,
                        color = lightBlack,
                        fontSize = 20.sp
                    ), modifier = Modifier.padding(start = 8.dp)
                )

                Text(text = "2 item ₹500",
                    style = TextStyle(
                        fontFamily = mFont.fsregular,
                        fontWeight = FontWeight.Normal,
                        color = lightBlack,
                        fontSize =12.sp
                    ), modifier = Modifier.padding(start = 8.dp)
                )
            }



        }




        Image(
            painterResource(id = R.drawable.ic_rateimage),
            contentDescription = "image",
            modifier = Modifier.padding(16.dp).fillMaxWidth().padding(24.dp),
            contentScale = ContentScale.Fit)

        inputHelp2(text = "helpc")

    }

}

@Composable
fun inputHelp2(text: String) {

    var inputReview by remember { mutableStateOf("") }
    var selectedStars by remember { mutableStateOf(0) }

    Spacer(modifier = Modifier.height(12.dp))

    Text(text = "Write a review",
        style = TextStyle(
            fontFamily = mFont.fsregular,
            color = lightBlack,
            fontSize = 16.sp
        )
    )

    Spacer(modifier = Modifier.height(12.dp))

    InputTextLarge(
        modifier = Modifier
            .padding(top = 2.dp, bottom = 4.dp),
        text = inputReview,
        color = lightBlack,
        maxLine = 6,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Default,
            keyboardType = KeyboardType.Text),
        onTextChange = { inputReview = it },
        maxLength = 200
    )

    Spacer(modifier = Modifier.height(12.dp))

    StarRating(
        selectedStars = selectedStars,
        onRatingChanged = { newRating ->
            selectedStars = newRating // Update selected stars
        }
    )
    Spacer(modifier = Modifier.height(48.dp))

    SmallButton(onClick = {

    }, text = "Submit")

}

@Composable
fun StarRating(
    modifier: Modifier = Modifier,
    maxStars: Int = 5,
    selectedStars: Int = 0,
    onRatingChanged: (Int) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        for (i in 1..maxStars) {
            val starColor = if (i <= selectedStars) lightOrange else lightGrey
            Icon(
                painter = painterResource(id = R.drawable.ic_star), // Replace with your star icon resource
                contentDescription = "Star $i",
                tint = starColor,
                modifier = Modifier.padding(2.dp)
                    .size(32.dp)
                    .clickable {
                        onRatingChanged(i) // Update rating when star is clicked
                    }
            )
        }
    }
}
