package application.rest

import commons.ErrorHandler
import io.javalin.Javalin
import org.koin.core.KoinComponent
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.get
import application.configuration.*
import application.rest.routes.JtiRouter
import commons.DataBaseConfig
import commons.TriggerConfig
import org.koin.core.inject


object App : KoinComponent {

    private lateinit var app: Javalin
    private val config: EnvironmentConfig by inject()
    private val databaseManager: DataBaseConfig by inject()
    private val triggerConfig: TriggerConfig by inject()

    private fun start() {
        startKoin {
            loadKoinModules(
                listOf(
                    jtiRouter,
                    jtiControllerModule,
                    jtiRepository,
                    jtiServiceImplModule,
                    environmentConfig,
                    dataBaseConfig,
                    triggerJob,
                    deleteExpiredJtiJob
                )
            )
        }
        app = Javalin.create().apply {
            exception(Exception::class.java, ErrorHandler()::handle)

        }.start(config.serverPort)


        app.routes {
            get<JtiRouter>().register()
        }

        databaseManager.connect()
        triggerConfig.start()
    }

    fun shutdown() {
        app.stop()
        stopKoin()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        start()
    }
}