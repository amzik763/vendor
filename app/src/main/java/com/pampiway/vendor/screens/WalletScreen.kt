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
import androidx.compose.material.DrawerState
import androidx.compose.material.ModalDrawer
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pampiway.vendor.R
import com.pampiway.vendor.SideNav
import com.pampiway.vendor.TopBar
import com.pampiway.vendor.ui.theme.lightBlack
import com.pampiway.vendor.utility.mFont
import com.pampiway.vendor.utility.myComponent
import kotlinx.coroutines.launch

@Composable
fun WalletScreen2(navController: NavController) {

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


            Text(text = "Wallet",
                style = TextStyle(
                    fontFamily = mFont.fsbold,
                    fontWeight = FontWeight.Bold,
                    color = lightBlack,
                    fontSize = 26.sp
                ), modifier = Modifier.padding(start = 8.dp)
            )
        }









    }

}


@Composable
fun WalletScreen3(navController: NavController, drawerState: DrawerState,switchOn: Boolean,
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