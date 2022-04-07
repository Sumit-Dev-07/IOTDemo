package com.hexalitics.mvvm_starter.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
open class BaseResponse(
    @field:SerializedName("ExceptionMessage")
    var exceptionMessage: String? = null,

    @field:SerializedName("ErrorCode")
    var errorCode: String? = null

): Parcelable
