package controllers.base.responses

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import spark.HaltException
import spark.Spark

open class BaseResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("error")
    val error: String? = null,
    @SerializedName("httpStatus")
    val httpStatus: Int
) {

    fun halt(gson: Gson): HaltException = Spark.halt(httpStatus, gson.toJson(this))
}