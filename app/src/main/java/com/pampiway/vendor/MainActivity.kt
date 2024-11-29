package com.pampiway.vendor

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Scaffold
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
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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
import com.amzi.mastercellusv2.allViewModels.RegisterViewModel
import com.amzi.mastercellusv2.allViewModels.factories.RegisterViewModelFactory
import com.amzi.mastercellusv2.networks.AuthAPIs
import com.amzi.mastercellusv2.networks.HomeAutoApi
import com.amzi.mastercellusv2.networks.RetrofitBuilder
import com.amzi.mastercellusv2.repository.AuthRepo
import com.amzi.mastercellusv2.repository.DeliveryRepo
import com.pampiway.vendor.components.CenterSwipeButton
import com.pampiway.vendor.components.CenterSwipeButton2
import com.pampiway.vendor.components.InputText
import com.pampiway.vendor.components.InputTextWithIcon
import com.pampiway.vendor.components.SmallButton
import com.pampiway.vendor.components.SmallButtonBorder
import com.pampiway.vendor.components.SmallComponents
import com.pampiway.vendor.components.SwipeButton
import com.pampiway.vendor.screens.CreateAccountScreen
import com.pampiway.vendor.screens.CreateRegister
import com.pampiway.vendor.screens.EditProfile
import com.pampiway.vendor.screens.HelpSupport
import com.pampiway.vendor.screens.History
import com.pampiway.vendor.screens.Logout
import com.pampiway.vendor.screens.Notification
import com.pampiway.vendor.screens.NotificationUser
import com.pampiway.vendor.screens.ParcelScreen
import com.pampiway.vendor.screens.WalletScreen2
import com.pampiway.vendor.screens.bookingScreen
import com.pampiway.vendor.screens.carTravelScreen
import com.pampiway.vendor.screens.couponscreen
import com.pampiway.vendor.screens.myMenu
import com.pampiway.vendor.screens.orderPlaced
import com.pampiway.vendor.screens.orderPlacing
import com.pampiway.vendor.screens.ratingScreen
import com.pampiway.vendor.screens.referandearnscreen
import com.pampiway.vendor.ui.theme.VendorTheme
import com.pampiway.vendor.ui.theme.darkGrey
import com.pampiway.vendor.ui.theme.lightBlack
import com.pampiway.vendor.ui.theme.mblue
import com.pampiway.vendor.ui.theme.mred
import com.pampiway.vendor.utility.NetworkMonitor
import com.pampiway.vendor.utility.Switch2
import com.pampiway.vendor.utility.mDialog
import com.pampiway.vendor.utility.mFont
import com.pampiway.vendor.utility.myComponent
import com.pampiway.vendor.utility.myComponent.authAPI
import com.pampiway.vendor.utility.myComponent.authRepo
import com.pampiway.vendor.utility.myComponent.homeAutoApi
import com.pampiway.vendor.utility.myComponent.deliveryRepo
import com.pampiway.vendor.utility.myComponent.navController
import com.pampiway.vendor.utility.myComponent.registerViewModel
import com.pampiway.vendor.utility.myComponent.registerViewModelFactory
import com.pampiway.vendor.utility.showLogs
import com.pampiway.vendor.utility.showSnackBarNow
import com.pampiway.vendor.utility.snacks
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    var initial = 0;
    val networkCallback = object : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            if(initial == 0) {
                initial++
                return
            }
            // Called when a network is available
            showLogs("MAIN: ","Connected")
            showSnackBarNow("Connected",applicationContext)
        }
        override fun onLost(network: Network) {
            if(initial == 0) {
                initial++
                return
            }
            // Called when a network is lost
            showLogs("MAIN: ","Disconnected")
            showSnackBarNow("No Network",applicationContext)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        enableEdgeToEdge()

        authAPI = RetrofitBuilder.create(this).create(AuthAPIs::class.java)
        homeAutoApi = RetrofitBuilder.create(this).create(HomeAutoApi::class.java)
        authRepo = AuthRepo(authAPI, context = applicationContext)
        deliveryRepo = DeliveryRepo(homeAutoApi, context = applicationContext)
        registerViewModelFactory = RegisterViewModelFactory(authRepo, deliveryRepo)
        registerViewModel = registerViewModelFactory.create(RegisterViewModel::class.java)

        setContent {

            VendorTheme {
                snacks.scaffoldState  = rememberScaffoldState()
                snacks.coroutineScope = rememberCoroutineScope()
                navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize(),
                    scaffoldState = snacks.scaffoldState
                    ) { innerPadding ->
                   /* Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )*/
                    AppNavigation(modifier = Modifier.padding(innerPadding))
                }
                val networkMonitor = NetworkMonitor(applicationContext)
                showLogs("temp",networkMonitor.checkNowForInternet().toString())
                networkMonitor.registerNetworkCallback(networkCallback)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
//        networkMonitor.unregisterNetworkCallback(networkCallback)

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
            if(registerViewModel.isErrorDialogVisible.value)
                mDialog(onDismiss = {
                myComponent.registerViewModel.hideErrorDialog()
            })
            SideNav(drawerState = drawerState,
                selectedRoute = currentRoute ?: "", // Pass the current route dynamically
                onItemSelected = { route ->
                    navController.navigate(route) {
//                        popUpTo(navController.graph.startDestinationId) { saveState = true }
//                        launchSingleTop = true
//                        restoreState = true
                    }
                }
            )
        },
        bottomBar = {
            // Bottom Navigation only on Dashboard and Wallet screens
            if (currentRoute in listOf("Home", "wallet", "History")) {
                BottomNav(navController,currentRoute.toString())

            }
        },
        content = {
            // Setup the NavHost to manage screen navigation
            NavHost(navController = navController, startDestination = "auth") {
                composable("auth") {
//                    AuthScreen()
//                EditProfile()
//                    ratingScreen(navController = navController)
//                    myMenu()
//                    orderPlaced()
//                    orderPlacing()
//                    NotificationUser(navController)
//                    referandearnscreen()
//                      couponscreen()
//                    SmallComponents()
//                    ParcelScreen(navController = navController)
//                    carTravelScreen(navController)
                    bookingScreen(navController)
                }
                composable("register") {
                    RegisterScreen()
                }
                composable("Home") {
                    DashboardScreen(navController, drawerState,switchOn = switchON,
                        onToggleChange = {
                            if(switchON){
                                switchON = false
                            }else switchON = true
                        })
                }
                composable("wallet") {
                    WalletScreen(navController, drawerState)
                }
                composable("wallet2") {
                    WalletScreen2(navController)
                }
                composable("offer") {
                    OfferScreen(navController)
                }
                composable("Help") {
                    HelpSupport(navController)
                }

                composable("Notification") {
                    Notification(navController)
                }


                composable("History") {
                    History(navController)
                }

                composable("account") {
                    AccountScreen(navController)
                }

                composable("Logout") {
                    Logout()
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
        "Help" to R.drawable.ic_help,  // Vector Icon
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
                    .clickable {
                        onItemSelected(label.lowercase())
                        coroutineScope.launch {
                            drawerState.close() // Close the sidebar
                        }
                    }
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
            selected = currentRoute == "Home", // Check if this route is selected
            selectedContentColor = mred, // Selected item tint
            unselectedContentColor = lightBlack, // Unselected item tint
            onClick = { navController.navigate("Home") }
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
            selected = currentRoute == "History", // Check if this route is selected
            selectedContentColor = mred, // Selected item tint
            unselectedContentColor = lightBlack, // Unselected item tint
            onClick = { navController.navigate("History") }
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
    CreateRegister()
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
    var currentRoute by remember { mutableStateOf("Home") }

    ModalDrawer(
        drawerState = drawerState,
        drawerContent = {
            SideNav(drawerState = drawerState,
                selectedRoute = currentRoute,
                onItemSelected = { route ->
                    currentRoute = route
                    navController.navigate(route) {
//                        popUpTo(navController.graph.startDestinationId) { saveState = true }
//                        launchSingleTop = true
//                        restoreState = true
                    }
                }
            )
        }
    ) {
        val coroutineScope = rememberCoroutineScope()
        // Dashboard content
        Column {
            TopBar(
                switchOn = switchOn,
                onMenuClick = { coroutineScope.launch {
                    drawerState.open()
                } },
                text = "Dashboard",
                isChecked = switchOn,
                onToggleChange = onToggleChange
            )

            Header()
        }
    }
}
@Composable
fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(vertical = 10.dp)
    ) {
        // Shadow Layer
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .offset(y = 3.dp) // Offset shadow below
                .graphicsLayer {
                    shadowElevation = 4.dp.toPx() // Use elevation for shadow
                    shape = RoundedCornerShape(0.dp)
                    clip = false
                } // Blur for shadow softness
//                .background(
////                    color = Color.Gray.copy(alpha = 0.3f), // Semi-transparent shadow
//                    shape = RoundedCornerShape(0.dp)
//                )
        )

        // Actual Content Layer
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(color = Color.White, shape = RoundedCornerShape(0.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Column A: Square Image
                Column(
                    modifier = Modifier
                        .size(60.dp)
                        .offset(x = (-30).dp) // Offset horizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_pizza), // Replace with your image
                        contentDescription = "Square Image",
                        modifier = Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Fit
                    )
                }

                // Column B:
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.7f) // Approx. 70% width
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Food Delivery",
                        color = lightBlack,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = mFont.fsmedium
                    )
                    Text(
                        text = "Aadhar Mart",
                        color = mred,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = mFont.fssemibold
                    )
                    Text(
                        text = "Baswa Road Mela ka Chola",
                        color = darkGrey,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = mFont.fsregular
                    )
                }

                Spacer(modifier = Modifier.width(12.dp)) // Spacer between columns

                // Column C: Two texts aligned center
                Column(
                    modifier = Modifier
                        .fillMaxWidth() // Approx. 30% width
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "10",
                        color = mred,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = mFont.fsmedium,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Mins",
                        color = lightBlack,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = mFont.fssemibold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun WalletScreen(navController: NavController, drawerState: DrawerState) {
    var currentRoute by remember { mutableStateOf("wallet") }

    ModalDrawer(
        drawerState = drawerState,
        drawerContent = {
            SideNav(drawerState = drawerState,
                selectedRoute = currentRoute,
                onItemSelected = { route ->
                    currentRoute = route
                    navController.navigate(route) {
//                        popUpTo(navController.graph.startDestinationId) { saveState = true }
//                        launchSingleTop = true
//                        restoreState = true
                    }
                }
            )
        }
    ) {

        Column(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .padding(vertical = 16.dp, horizontal = 12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {

                    Image(
                        painterResource(id = R.drawable.ic_arrow),
                        contentDescription = "back",
                        modifier = Modifier
                            .size(32.dp)
                            .clickable {
                                myComponent.navController.popBackStack()
                            },
                        contentScale = ContentScale.Fit
                    )


                    Text(
                        text = "Wallet",
                        style = TextStyle(
                            fontFamily = mFont.fsbold,
                            fontWeight = FontWeight.Bold,
                            color = lightBlack,
                            fontSize = 26.sp
                        ), modifier = Modifier.padding(start = 8.dp)
                    )
                }





            }


            // Wallet content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(12.dp)
                    .border(
                        width = 2.dp,
                        color = mred, // Change to your desired border color
                        shape = RoundedCornerShape(12.dp) // Adjust the corner radius as needed
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Total Earning",
                    style = TextStyle(
                        fontFamily = mFont.fsregular,
                        color = darkGrey,
                        fontSize = 17.sp
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))


                Text(
                    text = "₹4500",
                    style = TextStyle(
                        fontFamily = mFont.fssemibold,
                        color = mred,
                        fontSize = 26.sp
                    )
                )
            }
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


/*
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
*/

/*@Composable
fun inputComponent(text: String, value: String, onValueChange: (String) -> Unit) {
    Text(
        text = text,
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
        text = value,
        color = Color.Black,
        maxLine = 1,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text
        ),
        onTextChange = onValueChange,
        maxLength = 10
    )

    Spacer(modifier = Modifier.height(14.dp))
}*/



