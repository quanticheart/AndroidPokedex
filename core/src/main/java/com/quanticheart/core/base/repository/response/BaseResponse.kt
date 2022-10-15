package com.quanticheart.core.base.repository.response

import com.google.gson.annotations.SerializedName

//
// Created by Jonn Alves on 07/10/22.
//
class BaseResponse<T> {
    @SerializedName("status")
    val status = false

    @SerializedName("msg")
    val message: String = ""

    @SerializedName("data")
    var data: T? = null
}