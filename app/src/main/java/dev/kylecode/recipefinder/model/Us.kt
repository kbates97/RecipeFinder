package dev.kylecode.recipefinder.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Us(
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("unitLong")
    val unitLong: String,
    @SerializedName("unitShort")
    val unitShort: String
) : Serializable