package com.hexalitics.mvvm_starter.di.network

import javax.inject.Inject

class Repository @Inject constructor(private val retrofitApi: ApiService) {

    suspend fun getData() = retrofitApi.getData()
    suspend fun ledOn(ipAddress: String) = retrofitApi.ledOn("http://192.168.252.85/ledon")
    suspend fun ledOff(ipAddress: String) = retrofitApi.ledOff("http://192.168.252.85/ledoff")

}