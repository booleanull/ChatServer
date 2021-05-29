package controllers.auth.models

import com.google.gson.annotations.SerializedName

data class AuthData(
    @SerializedName("login")
    val login: String,
    @SerializedName("password")
    val password: String
)