package com.amzi.mastercellusv2.networks

import android.content.Context
import com.pampiway.vendor.utility.TokenStorage
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val (accessToken, _) = TokenStorage.getToken(context) ?: Pair("", "")
        // Proceed with the request
        val requestBuilder = chain.request().newBuilder()
        // Add Authorization header if access token exists
        if (accessToken.isNotEmpty()) {
//            requestBuilder.addHeader("Authorization", "Bearer $accessToken")
        }
        // Proceed with the request
        return chain.proceed(requestBuilder.build())
    }
}
