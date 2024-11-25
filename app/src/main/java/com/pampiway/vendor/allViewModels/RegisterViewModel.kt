package com.amzi.mastercellusv2.allViewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amzi.mastercellusv2.repository.AuthRepo
import com.amzi.mastercellusv2.repository.DeliveryRepo
import com.pampiway.vendor.utility.showLogs
import kotlinx.coroutines.launch

class RegisterViewModel(authRepo: AuthRepo, deliveryRepo: DeliveryRepo) : ViewModel() {

    var errors: MutableMap<String, String>? = null
    var isErrorDialogVisible = mutableStateOf(false)

    var errorMessage = mutableStateOf("")

    var mPassword = mutableStateOf("")
    var mUsername = mutableStateOf("")


    // Use mutableStateOf to track UI-related state changes
    var username = mutableStateOf("")
    var mobNum = mutableStateOf("")
    var user_id = mutableStateOf("")
    var folderName = mutableStateOf("")
    var parent_id = mutableStateOf("")
    var folder_id = mutableStateOf("")
    var current_parent_id = mutableStateOf("")

    // Folder names list
//    val folders = mutableStateListOf<String>()
    init {

    }

    var authRepo: AuthRepo = authRepo
    var hmeAutoRepo: DeliveryRepo = deliveryRepo

    fun verify(mobileNum: String, password: String, password_confirm: String, otp: String) {
        mobNum.value = mobileNum
        showLogs("LOGIN: ", mobNum.value)
        viewModelScope.launch {
//            authRepo.verify(mobileNum, password, password_confirm, otp)
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
    ): Boolean {
            return hmeAutoRepo.createAccount(
                name,
                phoneNumber,
                email,
                city,
                district,
                state,
                pincode,
                password,
                confirmPassword
            )
    }

    fun hideErrorDialog() {
        isErrorDialogVisible.value = false
    }


    fun showErrorDialog() {
        isErrorDialogVisible.value = true
    }
}

