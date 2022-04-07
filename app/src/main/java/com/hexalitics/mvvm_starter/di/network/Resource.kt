package com.hexalitics.mvvm_starter.di.network

import com.hexalitics.mvvm_starter.di.network.Status.ERROR
import com.hexalitics.mvvm_starter.di.network.Status.LOADING
import com.hexalitics.mvvm_starter.di.network.Status.SUCCESS

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(status = SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource<T> =
            Resource(status = ERROR, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> = Resource(status = LOADING, data = data, message = null)
    }
}