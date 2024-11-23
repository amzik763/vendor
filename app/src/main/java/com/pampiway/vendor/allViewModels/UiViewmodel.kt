package com.amzi.mastercellusv2.allViewModels

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UiViewmodel(context: Context) : ViewModel() {


    val mContext = context
    var showHomeMacId by mutableStateOf(false)
        private set

    var showMushMacId by mutableStateOf(false)
        private set

    fun toggleHomeMacIdVisibility() {
        showHomeMacId = !showHomeMacId
    }

    fun toggleMushMacIdVisibility() {
        showMushMacId = !showMushMacId
    }

    //SHARED PREFERENCES

    private val sharedPreferences: SharedPreferences
        get() = mContext.getSharedPreferences("PREFERNCES_NAME", Context.MODE_PRIVATE)

    fun macId(key: String, macId: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key, macId)
        editor.apply()
    }

    fun getMacId(key: String): String {
        return sharedPreferences.getString(key, "Not Registered") ?: "Not Registered"
    }

}
