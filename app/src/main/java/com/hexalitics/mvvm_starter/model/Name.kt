package com.hexalitics.mvvm_starter.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.annotation.Keep

@Keep
@Parcelize
data class Name(
    @SerializedName("title")
    var title: String?,
    @SerializedName("first")
    var first: String?,
    @SerializedName("last")
    var last: String?
) : Parcelable