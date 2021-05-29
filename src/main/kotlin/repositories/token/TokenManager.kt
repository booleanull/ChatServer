package repositories.token

import controllers.auth.models.AuthData

interface TokenManager {

    fun generateAuthToken(authData: AuthData): String

    fun validateAuthToken(token: String): Boolean
}