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
import com.pampiway.vendor.inputText
import com.pampiway.vendor.ui.theme.darkGrey
import com.pampiway.vendor.ui.theme.lightBlack
import com.pampiway.vendor.ui.theme.mblue
import com.pampiway.vendor.ui.theme.mred
import com.pampiway.vendor.utility.mFont
import com.pampiway.vendor.utility.myComponent
import com.pampiway.vendor.utility.myComponent.navController

@Composable
fun HelpSupport(navController: NavHostController) {

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
                modifier = Modifier.size(32.dp).clickable {
                    myComponent.navController.popBackStack()
                },
                contentScale = ContentScale.Fit)
            

            Text(text = "Help & Support",
                style = TextStyle(
                    fontFamily = mFont.fsbold,
                    fontWeight = FontWeight.Bold,
                    color = lightBlack,
                    fontSize = 26.sp
                ), modifier = Modifier.padding(start = 8.dp)
            ) 
        }
        

        /*Row(modifier = Modifier
            .padding(top = 12.dp)
            .fillMaxWidth()
            .height(150.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){

            Image(
                painterResource(id = R.drawable.logopampi),
                contentDescription = "logo",
                modifier = Modifier.size(150.dp),
                contentScale = ContentScale.Fit)

        }*/

        Divider(
            color = Color.White,              // Set the color of the divider
            thickness = 4.dp,                // Set the thickness of the divider
            modifier = Modifier.fillMaxWidth() // Make the divider take full width
        )



        Spacer(modifier = Modifier.height(4.dp))

        inputHelp(text = "helpc")

    }

}

@Composable
fun inputHelp(text: String) {

    var inputSubject by remember { mutableStateOf("") }
    var inputMessage by remember { mutableStateOf("") }

    Text(text = "Subject",
        style = TextStyle(
            fontFamily = mFont.fsregular,
            color = darkGrey,
            fontSize = 20.sp
        )
    )

    Spacer(modifier = Modifier.height(12.dp))

    InputText(
        modifier = Modifier
            .padding(top = 2.dp, bottom = 4.dp),
        text = inputSubject,
        color = Color.Black,
        maxLine = 1,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email),
        onTextChange = { inputSubject = it },
        maxLength = 10
    )


    Spacer(modifier = Modifier.height(24.dp))

    Text(text = "Message",
        style = TextStyle(
            fontFamily = mFont.fsregular,
            color = darkGrey,
            fontSize = 20.sp
        )
    )

    Spacer(modifier = Modifier.height(12.dp))

    InputTextLarge(
        modifier = Modifier
            .padding(top = 2.dp, bottom = 4.dp),
        text = inputMessage,
        color = Color.Black,
        maxLine = 1,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password),
        onTextChange = { inputMessage = it },
        maxLength = 10
    )







    Spacer(modifier = Modifier.height(48.dp))

    SmallButton(onClick = {
        navController.navigate("register")
    }, text = "Raise A Query")

    Spacer(modifier = Modifier.height(4.dp))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Divider(
            color = lightBlack, // Set the color of the divider
            thickness = 2.dp, // Set the thickness of the divider
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center) // Center the divider vertically within the Box
        )
        Text(
            text = "Or",
            style = TextStyle(
                fontFamily = mFont.fsmedium,
                fontWeight = FontWeight.Medium,
                color = lightBlack,
                fontSize = 20.sp
            ),
            modifier = Modifier
                .align(Alignment.Center) // Center the text within the Box
                .background(Color.White)
                .padding(8.dp) // Optional padding
        )


    }

    Spacer(modifier = Modifier.height(4.dp))


    SmallButtonBorder(onClick = {
        navController.navigate("dashboard")
    }, text = "Call Us")


}

