package com.amzi.mastercellusv2.repository

import android.content.Context
import com.amzi.mastercellusv2.networks.HomeAutoApi
import com.pampiway.vendor.utility.TokenStorage
import com.pampiway.vendor.utility.showLogs

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
    ) {

        try {

            val res = homeAutoApi.createAccount(email, password, name, phoneNumber, city, district, state, Integer.parseInt(pincode), confirmPassword )

            if(res.isSuccessful){
                showLogs("API FAILED", "API SUCCESS")

            }else{
                showLogs("API FAILED", "API FAILED")
            }
        }catch (e:Exception){
            showLogs("API FAILED", "API ERROR")

        }

    }


}
