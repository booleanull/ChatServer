package controllers.chat.models

import com.google.gson.annotations.SerializedName

data class SendData(
    @SerializedName("chatName")
    val chatName: String,
    @SerializedName("text")
    val text: String
)
