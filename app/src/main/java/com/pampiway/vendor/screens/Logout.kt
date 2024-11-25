package com.pampiway.vendor.screens

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun Logout() {

    val context = LocalContext.current as? Activity
    context?.finish()

}