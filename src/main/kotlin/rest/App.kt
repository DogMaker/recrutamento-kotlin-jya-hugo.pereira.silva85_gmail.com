package rest

import io.javalin.Javalin
import org.koin.core.KoinComponent
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.get
import repository.config.PostgresDBConfig
import rest.configuration.*
import rest.routes.EventsRoutes
import rest.routes.WebhookRoutes


object App : KoinComponent {

    private lateinit var app: Javalin

    private fun start() {
        startKoin {
            loadKoinModules(
                    listOf(
                            eventsRoutesModule,
                            webhooksRoutesModule,
                            eventsControllerModule,
                            webhookControllerModule,
                            eventServiceModule,
                            webhookServiceModule
                    )
            )
        }
        app = Javalin.create().apply {
            exception(Exception::class.java) { e, _ ->
                e.printStackTrace()
            }
            error(404) { ctx ->
                ctx.json("not found")
            }
        }.start(7171)


        app.routes {
            get<WebhookRoutes>().register()
            get<EventsRoutes>().register()
        }

        //get<PostgresDBConfig>().startConnection()
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