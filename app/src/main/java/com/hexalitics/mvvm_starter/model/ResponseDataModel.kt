package com.hexalitics.mvvm_starter.model

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.annotation.Keep

@Keep
@Parcelize
data class ResponseDataModel(
    @SerializedName("results")
    var results: ArrayList<Result>?/*,
    @SerializedName("info")
    var info: Info?*/
) : BaseResponse(), Parcelable