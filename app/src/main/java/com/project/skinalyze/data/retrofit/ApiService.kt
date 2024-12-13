package com.project.skinalyze.data.retrofit

import com.project.skinalyze.data.response.HistoryResponseItem
import com.project.skinalyze.data.response.LoginResponse
import com.project.skinalyze.data.response.PredictionResponse
import com.project.skinalyze.data.response.RegisterResponse
import com.project.skinalyze.data.response.UpdateUserResponse
import com.project.skinalyze.data.response.UserResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    fun registerUser(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("email") name: String,
//        @Field("avatar_url") avatar: String
    ) : Call<RegisterResponse>

    @FormUrlEncoded
    @POST("login")
    fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @Multipart
    @POST("get-disease-name")
    fun uploadImage(
        @Header("token") token: String,
        @Part image: MultipartBody.Part
    ): Call<PredictionResponse>

    @GET("get-user-history")
    fun getHistoryUser(
        @Header("token") token: String,
    ): Call<List<HistoryResponseItem>>

    @GET("get-user-data")
    fun getUserData(
        @Header("token") token: String,
    ): Call<UserResponse>

    @Multipart
    @POST("update-user")
    fun updateUser(
        @Header("token") token: String,
        @Part avatar_image: MultipartBody.Part
    ): Call<UpdateUserResponse>

    @FormUrlEncoded
    @POST("update-user")
    fun updateDataUser(
        @Header("token") token: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("name") name: String
    ): Call<UpdateUserResponse>
}