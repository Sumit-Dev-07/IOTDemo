package com.hexalitics.mvvm_starter.di.network

import javax.inject.Inject

class Repository @Inject constructor(private val retrofitApi: ApiService) {

    suspend fun getData() = retrofitApi.getData()

}