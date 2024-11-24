package com.pampiway.vendor.utility

import androidx.navigation.NavHostController
import com.amzi.mastercellusv2.allViewModels.HomeAppViewModel
import com.amzi.mastercellusv2.allViewModels.RegisterViewModel
import com.amzi.mastercellusv2.allViewModels.factories.RegisterViewModelFactory
import com.amzi.mastercellusv2.networks.AuthAPIs
import com.amzi.mastercellusv2.networks.HomeAutoApi
import com.amzi.mastercellusv2.repository.AuthRepo
import com.amzi.mastercellusv2.repository.DeliveryRepo

object myComponent {

    lateinit var navController: NavHostController
    lateinit var authAPI: AuthAPIs
    lateinit var homeAutoApi: HomeAutoApi
    lateinit var authRepo: AuthRepo
    lateinit var deliveryRepo: DeliveryRepo
    //    lateinit var mUiViewModelFactory:
//    lateinit var mUiViewModel: UiViewmodel
    lateinit var registerViewModelFactory: RegisterViewModelFactory
    lateinit var registerViewModel: RegisterViewModel
    lateinit var homeAppViewModel: HomeAppViewModel
}