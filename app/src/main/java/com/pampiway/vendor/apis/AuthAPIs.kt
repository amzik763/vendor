package com.amzi.mastercellusv2.networks

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
interface AuthAPIs {

    @FormUrlEncoded
    @POST("/api/delivery-boy/create")
    suspend fun createAzsccount(
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

    @FormUrlEncoded
    @POST("/master/api/confirmuser")
    suspend fun verify(
        @Field("mobile_number") mobile_number: String,
        @Field("password") password: String,
        @Field("password_confirm") password_confirm: String,
        @Field("otp") otp: String
    ): Response<String>

    @FormUrlEncoded
    @POST("/master/api/login")
    suspend fun login(
        @Field("username_or_mobile") username_or_mobile: String,
        @Field("password") password: String
    ): Response<String>

}

