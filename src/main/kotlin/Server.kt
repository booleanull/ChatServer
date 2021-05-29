import controllers.base.BaseController
import di.appModule
import di.daoModule
import org.koin.core.context.startKoin

import spark.Spark.port
import java.net.InetAddress
import java.text.SimpleDateFormat
import java.util.*

fun main(args: Array<String>) {
    val koinApplication = startKoin {
        modules(appModule + daoModule)
    }

    var port = 8080
    args.getOrNull(0)?.toIntOrNull()?.let { arg ->
        port = arg
    }

    val server: Server = koinApplication.koin.get()
    server.init(port)
}

class Server(private val controllers: List<BaseController>) {

    fun init(port: Int) {

        port(port)

        controllers.forEach { controller ->
            controller.start()
        }

        val simpleDateFormat = SimpleDateFormat("hh:mm dd/MM/yyyy")
        val date = simpleDateFormat.format(Date())
        val ip = InetAddress.getLocalHost()

        println("=======================================")
        println("Port: $port")
        println("IP address: ${ip.hostAddress}")
        println("Date: $date")
        println("=======================================\n")
    }
}