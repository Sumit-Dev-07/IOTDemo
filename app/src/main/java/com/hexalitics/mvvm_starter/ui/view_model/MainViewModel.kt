package com.hexalitics.mvvm_starter.ui.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hexalitics.mvvm_starter.di.network.Repository
import com.hexalitics.mvvm_starter.di.network.Resource
import com.hexalitics.mvvm_starter.model.ResponseDataModel
import com.hexalitics.mvvm_starter.model.Result
import com.hexalitics.mvvm_starter.utils.getErrorBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.net.InetAddress
import javax.inject.Inject

/**
 * Created by rahul on 05-04-2022
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: Repository) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val resultData = MutableLiveData<ArrayList<Result>>()

    fun getResultData() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val response = mainRepository.getData()
            if (response.isSuccessful) {
                val storeResponse = response.body() as ResponseDataModel
                emit(Resource.success(data = storeResponse))
            } else {
                withContext(Dispatchers.Main) {
                    onResultError(response)?.let {
                        emit(Resource.error(data = null, message = it))
                    }
                }
            }
        } catch (he: HttpException) {
            emit(Resource.error(data = null, message = he.message!!))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message!!))
        }

    }

    fun ledOn(ipAddress: String) = liveData(Dispatchers.IO) {
        try {
            val response = mainRepository.ledOn(ipAddress)
            if (response.isSuccessful) {
                emit(Resource.success(data = null))
            } else {
                withContext(Dispatchers.Main) {
                    onResultError(response)?.let {
                        emit(Resource.error(data = null, message = it))
                    }
                }
            }
        } catch (he: HttpException) {
            emit(Resource.error(data = null, message = he.message!!))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message!!))
        }

    }

    fun ledOff(ipAddress: String) = liveData(Dispatchers.IO) {
        try {
            val response = mainRepository.ledOff(ipAddress)
            if (response.isSuccessful) {
                emit(Resource.success(data = null))
            } else {
                withContext(Dispatchers.Main) {
                    onResultError(response)?.let {
                        emit(Resource.error(data = null, message = it))
                    }
                }
            }
        } catch (he: HttpException) {
            emit(Resource.error(data = null, message = he.message!!))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message!!))
        }

    }

    private fun onResultError(response: Response<*>): String? {
        val errorRe = response.getErrorBody(ResponseDataModel::class.java) as ResponseDataModel?
        return errorRe?.exceptionMessage
    }

    fun resultContent(): String {
        val result = resultData?.value?.get(0)
        return "Name: ${result?.name?.title} ${result?.name?.first} ${result?.name?.last}\n" +
                "Gender: ${result?.gender}\nEmail: ${result?.email}"
    }
}