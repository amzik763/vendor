package com.amzi.mastercellusv2.allViewModels

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeAppViewModel(private val context: Context):ViewModel(){

        val mContext = context


    init{
        viewModelScope.launch {
            Log.d("not","find me if u can")
        }

    }



    //SHARED PREFERENCES
    private val sharedPreferences: SharedPreferences
        get() = mContext.getSharedPreferences("PREFERNCES_NAME", Context.MODE_PRIVATE)

    fun getMacId(key: String): String {
        return sharedPreferences.getString(key, "Not Registered") ?: "Not Registered"
    }
}
