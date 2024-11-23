package com.amzi.mastercellusv2.networks

// TokenAuthenticator.kt
import android.content.Context
import android.util.Log
import com.pampiway.vendor.utility.TokenStorage
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

// Define the Auth API service for token refresh
interface AuthApiService {
    @FormUrlEncoded
    @POST("/master/api/refresh")
    suspend fun refreshToken(
        @Field("refresh") refreshToken: String
    ): retrofit2.Response<TokenResponse>
}

// Define the TokenResponse data class
data class TokenResponse(
    val access: String,
    val refresh: String
)

class TokenAuthenticator(private val context: Context) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        // Prevent infinite loops by checking if we've already attempted to authenticate
        if (responseCount(response) >= 2) {
            Log.e("Authenticator", "Failed to authenticate after multiple attempts.")
            return null // If we've already tried to authenticate twice, give up
        }

        val (_, refreshToken) = TokenStorage.getToken(context) ?: return null

        if (refreshToken.isEmpty()) {
            Log.e("Authenticator", "Refresh token is missing.")
            return null
        }

        // Attempt to refresh the token
        val newToken = refreshAccessToken(refreshToken)

        return if (newToken != null) {
            // Save the new tokens securely
            TokenStorage.saveToken(context, newToken.access, newToken.refresh)
            Log.d("TOKEN AUTHENTICATOR", "CALLED")

            // Retry the original request with the new access token
            response.request.newBuilder()
                .header("Authorization", "Bearer ${newToken.access}")
                .build()
        } else {
            null
        }
    }

    // Helper function to count the number of authentication attempts
    private fun responseCount(response: Response): Int {
        var res = response.priorResponse
        var count = 1
        while (res != null) {
            count++
            res = res.priorResponse
        }
        return count
    }

    // Function to refresh the access token using the refresh token
    private fun refreshAccessToken(refreshToken: String): TokenResponse? {
        // Create a separate OkHttpClient without interceptors to avoid infinite loops
        val client = okhttp3.OkHttpClient.Builder().build()

        // Build a Retrofit instance for the Auth API
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.5:8000") // Replace with your base URL
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val authApi = retrofit.create(AuthApiService::class.java)

        // Make a synchronous network call to refresh the token
        return try {
            val response = kotlinx.coroutines.runBlocking {
                authApi.refreshToken(refreshToken)
            }
            if (response.isSuccessful) {
                response.body()
            } else {
                Log.e("Authenticator", "Failed to refresh token: ${response.code()}")
                null
            }
        } catch (e: Exception) {
            Log.e("Authenticator", "Exception during token refresh: ${e.message}")
            null
        }
    }
}
