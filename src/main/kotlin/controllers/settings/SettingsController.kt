package controllers.settings

import controllers.base.BaseController
import spark.Spark

class SettingsController: BaseController {

    override fun start() {
        initOptions()
        initBeforeMethod()
    }

    private fun initOptions() {
        Spark.options("/*") { request, response ->

            val accessControlRequestHeaders = request.headers("Access-Control-Request-Headers")
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders)
            }

            val accessControlRequestMethod = request.headers("Access-Control-Request-Method")
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod)
            }

            "OK"
        }
    }

    private fun initBeforeMethod() {
        Spark.before("*") { req, res ->
            res.header("Access-Control-Allow-Origin", "*")
        }
    }
}