package com.quanticheart.repository.model

import com.google.gson.annotations.SerializedName

data class Images(

    @SerializedName("small") val small: String,
    @SerializedName("large") val large: String
)