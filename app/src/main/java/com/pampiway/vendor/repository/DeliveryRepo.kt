package com.amzi.mastercellusv2.repository

import android.content.Context
import android.widget.Toast
import com.amzi.mastercellusv2.networks.HomeAutoApi
import com.pampiway.vendor.response.createAccountRes
import com.pampiway.vendor.utility.TokenStorage
import com.pampiway.vendor.utility.myComponent
import com.pampiway.vendor.utility.showLogs
import retrofit2.Response

class DeliveryRepo(val homeAutoApi: HomeAutoApi, private val context: Context) {

    init {
        showLogs("Home Repo:","Home Repo Created")
    }

    suspend fun registerUserDevice( mobile_number: String, device_mac: String){
        try {

            // Retrieve the access token
            val accessToken = TokenStorage.getToken(context = context)

            // Log the raw access token for debugging
            showLogs("Home Repo:", "Raw Access Token: ${accessToken?.first ?: "No Token Found"}")

            // Ensure the access token is not null, remove all whitespace, and trim
            val token = accessToken?.first?.replace("\\s".toRegex(), "")?.trim()

            if (token.isNullOrBlank()) {
                showLogs("Home Repo Error:", "Access token is missing or empty.")
                return
            }

            // Append "Bearer " before the token with only one space
            val authorizationHeader = "Bearer $token"

            // Log the complete Authorization header
            showLogs("Home Repo:", "Authorization Header: '$authorizationHeader'")

            // Make the API call with the Authorization header
            val registerUserDevice = homeAutoApi.registerUserDevice(authorizationHeader, mobile_number, device_mac)
            if(registerUserDevice.isSuccessful){

                showLogs("Home Repo:","registerUserDevice Successful")

            }else{
                showLogs("Home Repo:","registerUserDevice Unsuccessful")
            }

        }catch (e: Exception){
            showLogs("Home Error",e.toString())

        }
    }

    suspend fun createAccount(
        name: String,
        phoneNumber: String,
        email: String,
        city: String,
        district: String,
        state: String,
        pincode: String,
        password: String,
        confirmPassword: String
    ) :Boolean{

        return try {

            val res = homeAutoApi.createAccount(email, password, name, phoneNumber, city, district, state, Integer.parseInt(pincode), confirmPassword )
            if(res.isSuccessful){
                showLogs("API SUC", "API SUCCESS")
                showLogs("API SUC", res.body().toString())
                myComponent.registerViewModel.showErrorDialog()
                myComponent.registerViewModel.createAccountRes =  res.body()
                showLogs("API SUCCESS",  myComponent.registerViewModel.createAccountRes?.message?:"")
                myComponent.registerViewModel.errorMessage.value = "Account Created Successfully!!"
                return true

            }else{
                showLogs("API FAILED", "API FAILED")
                showLogs("API FAiled", res.body().toString())

                myComponent.registerViewModel.createAccountRes =  res.body()
                showLogs("API FAILED", res.message()?:"Error")
                showLogs("API failed errorbody", res.errorBody().toString()?:"Error")
                showLogs("API FAILED",  myComponent.registerViewModel.createAccountRes?.message?:"")
                myComponent.registerViewModel.showErrorDialog()
                myComponent.registerViewModel.errorMessage.value = res.body()?.message?:"Error! Try again"
                return false
            }
        }catch (e:Exception){
            showLogs("bigerror",e.printStackTrace().toString())
            showLogs("API ERRR", "API ERROR")
            myComponent.registerViewModel.showErrorDialog()
            myComponent.registerViewModel.errorMessage.value = e.message.toString()
            return false

        }

    }


}
