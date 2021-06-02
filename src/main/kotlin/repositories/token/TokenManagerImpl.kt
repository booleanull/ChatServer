package repositories.token

import controllers.auth.models.AuthData
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import repositories.user.UserRepository

class TokenManagerImpl(
    private val userRepository: UserRepository
): TokenManager {

    private val currentTime: Long
        get() = System.currentTimeMillis()

    override fun generateAuthToken(authData: AuthData): String = Jwts.builder()
        .claim("login", authData.login)
        .claim("password", authData.password)
        .claim("currentTime", currentTime)
        .signWith(SignatureAlgorithm.HS512, Constants.JWT_SECRET).compact()

    override fun validateAuthToken(token: String): Boolean {
        try {
            val tokenBody = Jwts.parser()
                .setSigningKey(Constants.JWT_SECRET)
                .parseClaimsJws(token)
                .body

            val email = (tokenBody["login"] as? String) ?: return false
            val password = (tokenBody["password"] as? String) ?: return false

            val user = userRepository.getUser(AuthData(email, password)) ?: return false
            val userToken = user.token
            return userToken == token
        } catch (e: Exception) {
            return false
        }
    }
}