package controllers.chat.models

import com.google.gson.annotations.SerializedName

data class StartChatData(
    @SerializedName("login")
    val login: String
)