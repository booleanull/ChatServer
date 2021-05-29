package controllers.base

import com.google.gson.JsonParseException
import controllers.base.responses.BaseResponse
import controllers.base.responses.error.BadRequestErrorResponse
import controllers.base.responses.error.TokenNotFoundErrorResponse
import repositories.token.TokenManager
import spark.Request
import spark.Response
import spark.Spark

private val routeWrapper: (tokenManager: TokenManager?, transformer: (model: Any) -> String, handler: (request: Request, response: Response) -> BaseResponse, request: Request, response: Response) -> Any = { tokenManager, transformer, handler, request, response ->
    val resp = try {
        tokenManager?.let {
            val token = request.headers("token") ?: ""
            if (!tokenManager.validateAuthToken(token)) {
                throw TokenNotFoundErrorResponse.halt(transformer)
            }
        }
        handler.invoke(request, response)
    } catch (e: JsonParseException) {
        BadRequestErrorResponse
    }

    resp
}

fun post(path: String, route: (Request, Response) -> BaseResponse, transformer: (model: Any) -> String, tokenManager: TokenManager? = null) {
    Spark.post(path, { request: Request, response: Response -> routeWrapper.invoke(tokenManager, transformer, route, request, response) }, transformer)
}

fun get(path: String, route: (Request, Response) -> BaseResponse, transformer: (model: Any) -> String, tokenManager: TokenManager? = null) {
    Spark.get(path, { request: Request, response: Response -> routeWrapper.invoke(tokenManager, transformer, route, request, response) }, transformer)
}