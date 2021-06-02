package controllers.chat.response.ok

import com.google.gson.annotations.SerializedName
import controllers.base.responses.ok.BaseOkResponse

data class ChatOkResponse(
    @SerializedName("chat")
    val chat: ChatResponse
): BaseOkResponse()

data class ChatResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("message")
    val message: List<MessageResponse>
)

data class MessageResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("author")
    val author: UserResponse,
    @SerializedName("text")
    val text: String,
    @SerializedName("time")
    val time: String
)

data class UserResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("login")
    val login: String,
    @SerializedName("name")
    val name: String
)
