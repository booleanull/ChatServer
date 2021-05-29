package di

import databases.chat.ChatDao
import databases.user.UserDao
import org.hibernate.cfg.Configuration
import org.koin.dsl.module

val daoModule = module {
    single { Configuration().configure().buildSessionFactory() }
    single { UserDao(get()) }
    single { ChatDao(get()) }
}