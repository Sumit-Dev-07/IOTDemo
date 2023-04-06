package com.hexalitics.mvvm_starter.di.network

import com.hexalitics.mvvm_starter.model.ResponseDataModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("api")
    suspend fun getData(): Response<ResponseDataModel?>

    @GET
    suspend fun ledOn(@Url url : String ): Response<ResponseBody?>
    @GET
    suspend fun ledOff(@Url url : String ): Response<ResponseBody?>

}