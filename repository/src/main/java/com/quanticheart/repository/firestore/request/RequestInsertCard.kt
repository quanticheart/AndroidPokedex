package com.quanticheart.repository.firestore.request

import com.google.gson.annotations.SerializedName

data class RequestInsertCard(
    @SerializedName("cards")
    val list: ArrayList<InsertCard>? = null,
)

data class InsertCard(
    @SerializedName("cardID")
    val cardID: String = "missingNo",
    @SerializedName("name")
    val name: String = "missingNo"
)
