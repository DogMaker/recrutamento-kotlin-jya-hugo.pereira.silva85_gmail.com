package rest

import io.javalin.Javalin
import org.koin.core.KoinComponent
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.get
import rest.configuration.*
import rest.routes.JtiRouter


object App : KoinComponent {

    private lateinit var app: Javalin

    private fun start() {
        startKoin {
            loadKoinModules(
                    listOf(
                            webhooksRoutesModule,
                            jtiControllerModule,
                            jtiServiceModule
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
            get<JtiRouter>().register()
        }
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