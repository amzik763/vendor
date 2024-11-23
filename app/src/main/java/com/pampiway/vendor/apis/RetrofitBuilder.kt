package com.amzi.mastercellusv2.networks

import android.content.Context
import android.util.Log
import com.pampiway.vendor.utility.BASE_URL
import com.pampiway.vendor.utility.TokenStorage
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MyCookieJar(private val context: Context) : CookieJar {
    private val cookieStore: HashMap<String, List<Cookie>> = HashMap()

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        // Save cookies for the domain
        cookieStore[url.host] = cookies

        // Extract access and refresh tokens from the cookies
        var accessToken: String? = null
        var refreshToken: String? = null

        cookies.forEach { cookie ->
            when (cookie.name) {
                "access" -> accessToken = cookie.value
                "refresh" -> refreshToken = cookie.value
            }
        }

        if (accessToken != null && refreshToken != null) {
            // Save tokens securely
//            TokenStorage.saveToken(context, accessToken!!, refreshToken!!)
            // Optionally log tokens
            Log.d("COOKIES", "Access Token: $accessToken")
            Log.d("COOKIES", "Refresh Token: $refreshToken")

            Log.d("COOKIES", "Access Token: ${TokenStorage.getToken(context)?.first}")
            Log.d("COOKIES", "Access Token: ${TokenStorage.getToken(context)?.second}")
        }
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        // Return cookies for the domain
        return cookieStore[url.host] ?: ArrayList()
    }
}

object RetrofitBuilder {
//    private const val BASE_URL = "http://192.168.1.45:8002" // Replace with your actual base URL

    fun create(context: Context): Retrofit {
        // Logging interceptor for debugging3
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        // Build the OkHttpClient with interceptors and authenticator
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(AuthInterceptor(context)) // Adds the Authorization header
            .authenticator(TokenAuthenticator(context)) // Handles token refresh
            .cookieJar(MyCookieJar(context)) // Optional: Manage cookies if needed
            .build()
        // Build and return the Retrofit instance
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}




