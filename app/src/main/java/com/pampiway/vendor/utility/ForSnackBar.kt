package com.pampiway.vendor.utility

import android.content.Context
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object snacks{
    lateinit var scaffoldState: ScaffoldState
    lateinit var coroutineScope: CoroutineScope
}


fun showConnectedSnackBar(s: String, applicationContext: Context){
    snacks.coroutineScope.launch {
        delay(2000)
        snacks.scaffoldState.snackbarHostState.showSnackbar(s,"OK",
            SnackbarDuration.Short)


    }
}

fun showDisconnectedSnackBar(s: String, applicationContext: Context){
    snacks.coroutineScope.launch {
        delay(2000)
        val snackBarResult =  snacks.scaffoldState.snackbarHostState.showSnackbar(s,"Retry",
            SnackbarDuration.Long)
        when(snackBarResult){
            SnackbarResult.ActionPerformed ->{
                val networkMonitor = NetworkMonitor(applicationContext)
                showLogs("temp",networkMonitor.checkNowForInternet().toString())

                val status = networkMonitor.checkNowForInternet()

                if(status){
                    delay(2000)
                    showConnectedSnackBar("Connected", applicationContext)
                }else{
                    delay(2000)
                    showDisconnectedSnackBar("No Network", applicationContext)
                }

            }
            SnackbarResult.Dismissed ->{

            }

        }

    }
}

fun showSnackBarNow(s: String, applicationContext: Context) {

    if(s=="Connected"){
       showConnectedSnackBar(s,applicationContext)
    }else if(s=="No Network"){
        showDisconnectedSnackBar(s,applicationContext)
    }

}
