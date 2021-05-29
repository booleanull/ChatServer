package di

import Server
import com.google.gson.GsonBuilder
import controllers.auth.AuthController
import controllers.chat.ChatController
import databases.chat.ChatDao
import databases.user.UserDao
import org.hibernate.cfg.Configuration
import org.koin.dsl.module
import repositories.chat.ChatRepository
import repositories.chat.ChatRepositoryImpl
import repositories.token.TokenManager
import repositories.token.TokenManagerImpl
import repositories.user.UserRepository
import repositories.user.UserRepositoryImpl

val appModule = module {

    single {
        Server(listOf(get<AuthController>(), get<ChatController>()))
    }

    single {
        GsonBuilder().create()
    }

    single { AuthController(get(), get(), get()) }
    single { ChatController(get(), get(), get(), get()) }

    single<UserRepository> { UserRepositoryImpl(get()) }
    single<TokenManager> { TokenManagerImpl(get()) }
    single<ChatRepository> { ChatRepositoryImpl(get()) }
}