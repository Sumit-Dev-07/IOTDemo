package com.hexalitics.mvvm_starter.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.annotation.Keep

@Keep
@Parcelize
data class Result(
    @SerializedName("gender")
    var gender: String?,
    @SerializedName("name")
    var name: Name?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("phone")
    var phone: String?,
    @SerializedName("cell")
    var cell: String?,
    @SerializedName("picture")
    var picture: Picture?,
    @SerializedName("nat")
    var nat: String?
) : Parcelable