package com.amzi.mastercellusv2.networks

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface HomeAutoApi {
    @GET("/master/api/protected")
    suspend fun protected(
        @Header("Authorization") accessToken: String
    ): Response<String>


    @FormUrlEncoded
    @POST("/api/delivery-boy/create")
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


    @FormUrlEncoded
    @POST("/homeautomation/api/registeruserdevice")
    suspend fun registerUserDevice(
        @Header("Authorization") authorizationHeader: String,
        @Field("mobile_number") mobile_number: String,
        @Field("device_mac") device_mac: String
    ): Response<String>

    @FormUrlEncoded
    @POST("/master/api/createfolder")
    suspend fun createFolder(
        @Header("Authorization") authorizationHeader: String,
        @Field("name") name: String,
        @Field("parent_id") parent_id: String,
        @Field("user") user: String
    ) : Response<String>

    @FormUrlEncoded
    @POST("/master/api/getfolderandfile")
    suspend fun getFolderAndFile(
        @Header("Authorization") authorizationHeader: String,
        @Field("user_id") user: String,
        @Field("parent_id") parent_id: String
    ) : Response<String>
}