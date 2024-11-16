package com.pampiway.vendor

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pampiway.vendor.components.InputText
import com.pampiway.vendor.components.InputTextWithIcon
import com.pampiway.vendor.components.SmallButton
import com.pampiway.vendor.components.SmallButtonBorder
import com.pampiway.vendor.ui.theme.VendorTheme
import com.pampiway.vendor.ui.theme.darkGrey
import com.pampiway.vendor.ui.theme.lightBlack
import com.pampiway.vendor.ui.theme.mblue
import com.pampiway.vendor.ui.theme.mred
import com.pampiway.vendor.utility.Switch2
import com.pampiway.vendor.utility.mFont
import com.pampiway.vendor.utility.myComponent.navController
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            VendorTheme {
                navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   /* Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )*/
                    AppNavigation(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    // Setup NavController

    // Drawer state
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
//    var isChecked by remember { mutableStateOf(false) }
    var switchON by remember { mutableStateOf(false) }

    // Scaffold with conditional bottom and side navigation
    Scaffold(
        drawerContent = {
            // Side Navigation (Drawer content)
            SideNav(drawerState = drawerState,
                selectedRoute = currentRoute ?: "", // Pass the current route dynamically
                onItemSelected = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        },
        bottomBar = {
            // Bottom Navigation only on Dashboard and Wallet screens
            if (currentRoute in listOf("dashboard", "wallet")) {
                BottomNav(navController,currentRoute.toString())

            }
        },
        content = {
            // Setup the NavHost to manage screen navigation
            NavHost(navController = navController, startDestination = "auth") {
                composable("auth") {
                    AuthScreen()
                }
                composable("register") {
                    RegisterScreen()
                }
                composable("dashboard") {
                    DashboardScreen(navController, drawerState,switchOn = switchON,
                        onToggleChange = {
                            if(switchON){
                                switchON = false
                            }else switchON = true
                        })
                }
                composable("wallet") {
                    WalletScreen(navController, drawerState,switchOn = switchON,
                        onToggleChange = {
                            if(switchON){
                                switchON = false
                            }else switchON = true
                        })
                }
                composable("offer") {
                    OfferScreen(navController)
                }
                composable("account") {
                    AccountScreen(navController)
                }
            }
        }
    )
}

@Composable
fun SideNav(
    drawerState: DrawerState,
    selectedRoute: String,
    onItemSelected: (String) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val items = listOf(
        "Home" to Icons.Default.Home,  // Vector Icon
        "Account" to Icons.Default.AccountCircle,  // Vector Icon
        "Wallet" to R.drawable.ic_wallet,  // Drawable Resource ID
        "Offer" to R.drawable.ic_offer,  // Drawable Resource ID
        "Notification" to Icons.Default.Notifications,  // Vector Icon
        "Logout" to Icons.Default.ExitToApp  // Vector Icon
    )

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 64.dp, start = 12.dp, end = 12.dp, bottom = 12.dp)
    ) {
        // Top Row with name and close icon
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Menu",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                tint = Color.Red,
                modifier = Modifier.clickable {
                    coroutineScope.launch {
                        drawerState.close() // Close the sidebar
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Menu items
        items.forEach { (label, icon) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemSelected(label.lowercase()) }
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Check if icon is an ImageVector or drawable resource ID
                if (icon is ImageVector) {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        tint = if (selectedRoute.lowercase() == label.lowercase()) Color.Red else Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    // For drawable resources, use painterResource
                    Icon(
                        painter = painterResource(id = icon as Int),  // Correct usage of painterResource
                        contentDescription = label,
                        tint = if (selectedRoute.lowercase() == label.lowercase()) Color.Red else Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = label,
                    fontSize = 17.sp,
                    color = if (selectedRoute.lowercase() == label.lowercase()) Color.Red else Color.Gray
                )
            }
        }
    }
}


@Composable
fun BottomNav(navController: NavController, currentRoute: String) {
    BottomNavigation(
        backgroundColor = Color.White // Set the background color to white
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    Icons.Default.Home,
                    contentDescription = "Home",
                    modifier = Modifier.size(20.dp) // Set icon size to 20.dp
                )
            },
            label = { Text("Home") },
            selected = currentRoute == "dashboard", // Check if this route is selected
            selectedContentColor = mred, // Selected item tint
            unselectedContentColor = lightBlack, // Unselected item tint
            onClick = { navController.navigate("dashboard") }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.ic_wallet),
                    contentDescription = "Wallet",
                    modifier = Modifier.size(20.dp) // Set icon size to 20.dp
                )
            },
            label = { Text("Wallet") },
            selected = currentRoute == "wallet", // Check if this route is selected
            selectedContentColor = mred, // Selected item tint
            unselectedContentColor = lightBlack, // Unselected item tint
            onClick = { navController.navigate("wallet") }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.ic_history),
                    contentDescription = "History",
                    modifier = Modifier.size(20.dp) // Set icon size to 20.dp
                )
            },
            label = { Text("History") },
            selected = currentRoute == "account", // Check if this route is selected
            selectedContentColor = mred, // Selected item tint
            unselectedContentColor = lightBlack, // Unselected item tint
            onClick = { navController.navigate("account") }
        )
        // Add more BottomNavigationItem if needed
    }
}

@Composable
fun TopBar(switchOn: Boolean,
    onMenuClick: () -> Unit,
    text: String,
    isChecked: Boolean,
    onToggleChange: (Boolean) -> Unit
) {
    TopAppBar(modifier = Modifier.statusBarsPadding(),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Menu Icon on the left
                IconButton(onClick = onMenuClick) {
                    Icon(Icons.Default.Menu, contentDescription = "Open Drawer")
                }

                // Centered Text
                Text(
                    text =
                        if(switchOn) "Online  " else "Offline  "
                    ,
                    style = TextStyle(
                        fontFamily = mFont.fssemibold,
                        fontWeight = FontWeight.SemiBold,
                        color = lightBlack,
                        fontSize = 20.sp
                    ),
                    modifier = Modifier
                        .background(Color.White)
                        .padding(8.dp) // Optional padding
                )

                // Toggle Switch on the right
//                Switch(checked = isChecked, onCheckedChange = onToggleChange)
                Switch2(switchON = switchOn, onToggleChange = onToggleChange)
            }
        },
        backgroundColor = Color.White
    )
}

@Composable
fun AuthScreen() {
    GreetingPreview()
}

@Composable
fun RegisterScreen() {
    CreateAccountScreen()
}


@Composable
fun DashboardScreen(navController: NavController,
                    drawerState: DrawerState,
                    switchOn: Boolean,
                    onToggleChange: (Boolean) -> Unit) {
    var currentRoute by remember { mutableStateOf("dashboard") }

    ModalDrawer(
        drawerState = drawerState,
        drawerContent = {
            SideNav(drawerState = drawerState,
                selectedRoute = currentRoute,
                onItemSelected = { route ->
                    currentRoute = route
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) {
        val coroutineScope = rememberCoroutineScope()
        // Dashboard content
        Column() {
            TopBar(
                switchOn = switchOn,
                onMenuClick = { coroutineScope.launch {
                    drawerState.open()
                } },
                text = "Dashboard",
                isChecked = switchOn,
                onToggleChange = onToggleChange
            )
            Text("Welcome to the Dashboard!")
        }
    }
}

@Composable
fun WalletScreen(navController: NavController, drawerState: DrawerState,switchOn: Boolean,
                 onToggleChange: (Boolean) -> Unit) {
    var currentRoute by remember { mutableStateOf("wallet") }

    ModalDrawer(
        drawerState = drawerState,
        drawerContent = {
            SideNav(drawerState = drawerState,
                selectedRoute = currentRoute,
                onItemSelected = { route ->
                    currentRoute = route
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) {
        // Wallet content
        val coroutineScope = rememberCoroutineScope()
        Column() {
            TopBar(
                switchOn = switchOn,
                onMenuClick = { coroutineScope.launch {
                    drawerState.open()
                } },
                text = "Wallet",
                isChecked = switchOn,
                onToggleChange = onToggleChange
            )
            Text("Welcome to the Wallet!")
        }
    }
}

@Composable
fun OfferScreen(navController: NavController) {
    Column {
        Text("FAQs Screen")
        Button(onClick = { navController.popBackStack() }) {
            Text("Back to Dashboard")
        }
    }
}

@Composable
fun AccountScreen(navController: NavController) {
    Column {
        Text("Help Screen")
        Button(onClick = { navController.popBackStack() }) {
            Text("Back to Dashboard")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAppNavigation() {
    AppNavigation()
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
            .padding(top = 32.dp)
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
        Spacer(modifier = Modifier.height(36.dp))
        SmallButton(onClick = {
            navController.navigate("dashboard")
        }, text = "Create")
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
        navController.navigate("register")
    }, text = "Submit")

    Spacer(modifier = Modifier.height(12.dp))

    
    SmallButtonBorder(onClick = {
        navController.navigate("dashboard")
    }, text = "Become A Delivery Partner")
    

}


