package dev.kylecode.recipefinder.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Measures(
    @SerializedName("metric")
    val metric: Metric,
    @SerializedName("us")
    val us: Us
) : Serializable