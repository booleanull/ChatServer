package controllers.profile.responses.ok

import com.google.gson.annotations.SerializedName
import controllers.base.responses.ok.BaseOkResponse

data class EditOkResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("login")
    val login: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("token")
    val token: String
): BaseOkResponse()
