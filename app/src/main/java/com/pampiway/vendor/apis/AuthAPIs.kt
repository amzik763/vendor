package com.amzi.mastercellusv2.networks

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
interface AuthAPIs {

    @FormUrlEncoded
    @POST("/api/deliveryboy/create")
    suspend fun createAccount(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("full_name") fullName: String,
        @Field("phone_number") phoneNumber: String,
        @Field("city") city: String,
        @Field("district") district: String,
        @Field("state") state: String,
        @Field("pincode") pincode: Int,
        @Field("confirm_password") confirmPassword: String
    ): Response<String>



}

