package repositories.user

import controllers.auth.models.AuthData
import repositories.user.model.User

interface UserRepository {

    fun createUser(user: User): Int

    fun getUser(authData: AuthData): User?

    fun getUserByToken(token: String): User?

    fun getUserByLogin(login: String): User?

    fun saveUser(user: User): User?

    fun removeUser(user: User)
}