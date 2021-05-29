package di

import Server
import com.google.gson.GsonBuilder
import controllers.auth.AuthController
import databases.user.UserDao
import org.hibernate.cfg.Configuration
import org.koin.dsl.module
import repositories.token.TokenManager
import repositories.token.TokenManagerImpl
import repositories.user.UserRepository
import repositories.user.UserRepositoryImpl

val appModule = module {

    single {
        Server(listOf(get<AuthController>()))
    }

    single {
        GsonBuilder().create()
    }

    single { AuthController(get(), get(), get()) }

    single<UserRepository> {
        UserRepositoryImpl(get())
    }
    single<TokenManager> { TokenManagerImpl(get()) }

    single { Configuration().configure().buildSessionFactory() }
    single { UserDao(get()) }
}