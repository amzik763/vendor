package com.amzi.mastercellusv2.repository

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.amzi.mastercellusv2.networks.AuthAPIs
import com.pampiway.vendor.utility.TokenStorage
import com.pampiway.vendor.utility.showLogs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepo(authAPIs: AuthAPIs,private val context: Context) {
    init {
        showLogs("Repo:","Created")
    }
    val authAPI = authAPIs

    /*suspend fun verify( mobile_number: String, password: String, password_confirm: String, otp: String){

        try{
            val verifyRes = authAPI.verify(mobile_number, password, password_confirm, otp)
            if(verifyRes.isSuccessful){
                showLogs("VERIFICATION","Verification Successful")
            }else{
                showLogs("VERIFICATION","Verification unSuccessful" + verifyRes.errorBody().toString())
            }
        }
        catch (e:Exception){
            showLogs("Error: ",e.toString())
        }
    }
*/

    // Optional: Logout function to clear tokens
    fun logout() {
        TokenStorage.clearToken(context)
    }
}
