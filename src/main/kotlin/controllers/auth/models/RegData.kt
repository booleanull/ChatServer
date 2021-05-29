package controllers.auth.models

import com.google.gson.annotations.SerializedName

data class RegData(
    @SerializedName("login")
    val login: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("password")
    val password: String
)