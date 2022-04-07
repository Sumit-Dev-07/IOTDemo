package com.hexalitics.mvvm_starter.utils

import com.google.gson.Gson
import retrofit2.Response

/**
 * Created by rahul on 05-04-2022
 */
fun Response<*>.getErrorBody(objectClass: Class<*>): Any? {
    return try {
        val body = this.errorBody()
        val adapter = Gson().getAdapter(objectClass)
        adapter.fromJson(body?.string())
    } catch (e: Exception) {
        null
    }
}