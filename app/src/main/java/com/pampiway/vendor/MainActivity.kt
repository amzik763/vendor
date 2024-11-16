package com.pampiway.vendor

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pampiway.vendor.components.InputText
import com.pampiway.vendor.components.InputTextWithIcon
import com.pampiway.vendor.components.SmallButton
import com.pampiway.vendor.components.SmallButtonBorder
import com.pampiway.vendor.ui.theme.VendorTheme
import com.pampiway.vendor.ui.theme.darkGrey
import com.pampiway.vendor.ui.theme.lightBlack
import com.pampiway.vendor.ui.theme.lightGrey
import com.pampiway.vendor.ui.theme.mblue
import com.pampiway.vendor.ui.theme.mred
import com.pampiway.vendor.utility.mFont

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VendorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
//    GreetingPreview()
    CreateAccountScreen()
}
@Composable
fun CreateAccountScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .verticalScroll(rememberScrollState()) // Enable vertical scrolling
            .padding(vertical = 16.dp, horizontal = 12.dp)
    ) {
        Text(
            text = "Create Account",
            style = TextStyle(
                fontFamily = mFont.fsbold,
                fontWeight = FontWeight.Bold,
                color = mred,
                fontSize = 26.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(36.dp))

        inputComponent(text = "Name")
        inputComponent(text = "Phone Number")
        inputComponent(text = "Email")
        inputComponent(text = "City")
        inputComponent(text = "District")
        inputComponent(text = "State")
        inputComponent(text = "Pincode")
        inputComponent(text = "Password")
        inputComponent(text = "Confirm Password")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VendorTheme {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(vertical = 16.dp, horizontal = 12.dp)
        ){
            Text(text = "Let's Login",
                style = TextStyle(
                    fontFamily = mFont.fsbold,
                    fontWeight = FontWeight.Bold,
                    color = lightBlack,
                    fontSize = 26.sp
                )
            )

            Row(modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
                .height(150.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center){

                Image(painterResource(id = R.drawable.logopampi),
                    contentDescription = "logo",
                    modifier = Modifier.size(150.dp),
                    contentScale = ContentScale.Fit)

            }

            Divider(
                color = Color.White,              // Set the color of the divider
                thickness = 4.dp,                // Set the thickness of the divider
                modifier = Modifier.fillMaxWidth() // Make the divider take full width
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Divider(
                    color = mred, // Set the color of the divider
                    thickness = 4.dp, // Set the thickness of the divider
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center) // Center the divider vertically within the Box
                )
                Text(
                    text = "DRIVER",
                    style = TextStyle(
                        fontFamily = mFont.fsbold,
                        fontWeight = FontWeight.Bold,
                        color = mred,
                        fontSize = 26.sp
                    ),
                    modifier = Modifier
                        .align(Alignment.Center) // Center the text within the Box
                        .background(Color.White)
                        .padding(8.dp) // Optional padding
                )


            }


            Spacer(modifier = Modifier.height(48.dp))

            inputText(text = "abc")

        }

    }
}

@Composable
fun inputComponent(text: String){

    var input by remember { mutableStateOf("") }

    Text(text = text,
        style = TextStyle(
            fontFamily = mFont.fsregular,
            color = darkGrey,
            fontSize = 17.sp
        )
    )

    Spacer(modifier = Modifier.height(6.dp))

    InputText(
        modifier = Modifier
            .padding(top = 2.dp, bottom = 4.dp),
        text = input,
        color = Color.Black,
        maxLine = 1,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text),
        onTextChange = { input = it },
        maxLength = 10
    )

    Spacer(modifier = Modifier.height(14.dp))


}

@Composable
fun inputText(text: String) {

    var inputEmail by remember { mutableStateOf("") }
    var inputPassword by remember { mutableStateOf("") }

    Text(text = "E-Mail",
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
        text = inputEmail,
        color = Color.Black,
        maxLine = 1,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email),
        onTextChange = { inputEmail = it },
        maxLength = 10
    )


    Spacer(modifier = Modifier.height(24.dp))

    Text(text = "Password",
        style = TextStyle(
            fontFamily = mFont.fsregular,
            color = darkGrey,
            fontSize = 20.sp
        )
    )

    Spacer(modifier = Modifier.height(12.dp))

    InputTextWithIcon(
        modifier = Modifier
            .padding(top = 2.dp, bottom = 4.dp),
        text = inputEmail,
        color = Color.Black,
        maxLine = 1,
        iconResId = R.drawable.eyepassword,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password),
        onTextChange = { inputPassword = it },
        maxLength = 10
    )

    Spacer(modifier = Modifier.height(2.dp))


    Text(
        text = "Forgot Password",
        style = TextStyle(
            fontFamily = mFont.fsregular,
            color = mblue,
            fontSize = 18.sp,
            textAlign = TextAlign.End // Align the text to the end
        ),
        modifier = Modifier.fillMaxWidth() // Make the Text take the maximum width
    )


    Spacer(modifier = Modifier.height(48.dp))
    
    SmallButton(onClick = {

    }, text = "Submit")

    Spacer(modifier = Modifier.height(12.dp))

    
    SmallButtonBorder(onClick = {

    }, text = "Become A Delivery Partner")
    

}


