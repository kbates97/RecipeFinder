package dev.kylecode.recipefinder.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class WinePairing(
    @SerializedName("pairedWines")
    val pairedWines: List<String>,
    @SerializedName("pairingText")
    val pairingText: String,
    @SerializedName("productMatches")
    val productMatches: List<ProductMatche>
) : Serializable