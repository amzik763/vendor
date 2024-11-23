package com.pampiway.vendor.utility

import android.content.Context
import android.content.SharedPreferences

object TokenStorage {
    private const val PREFS_NAME = "MyPrefs"
    private const val ACCESS_TOKEN_KEY = "ACCESS_TOKEN"
    private const val REFRESH_TOKEN_KEY = "REFRESH_TOKEN"

    // Save tokens securely
    fun saveToken(context: Context, accessToken: String, refreshToken: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Encrypt the tokens
//        val encryptedAccessToken = EncryptionUtil.encrypt(accessToken)
//        val encryptedRefreshToken = EncryptionUtil.encrypt(refreshToken)

        // Save encrypted tokens to SharedPreferences
        editor.putString(ACCESS_TOKEN_KEY, accessToken)
        editor.putString(REFRESH_TOKEN_KEY, refreshToken)
        editor.apply()
    }

    // Retrieve tokens
    fun getToken(context: Context): Pair<String, String>? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        val encryptedAccessToken = sharedPreferences.getString(ACCESS_TOKEN_KEY, null)
        val encryptedRefreshToken = sharedPreferences.getString(REFRESH_TOKEN_KEY, null)

        return if (encryptedAccessToken != null && encryptedRefreshToken != null) {
            try {
                // Decrypt the tokens
//                val accessToken = EncryptionUtil.decrypt(encryptedAccessToken)
//                val refreshToken = EncryptionUtil.decrypt(encryptedRefreshToken)
                Pair(encryptedAccessToken, encryptedRefreshToken)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        } else {
            null
        }
    }

    // Clear tokens (e.g., on logout)
    fun clearToken(context: Context) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
    }
}
