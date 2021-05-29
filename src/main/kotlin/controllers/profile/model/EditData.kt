package controllers.profile.model

import com.google.gson.annotations.SerializedName

data class EditData (
    @SerializedName("name")
    val name: String,
    @SerializedName("photo")
    val photo: String
)