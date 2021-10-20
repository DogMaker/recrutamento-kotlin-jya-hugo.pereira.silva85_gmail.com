package rest.routes

import io.javalin.apibuilder.ApiBuilder
import rest.controller.JtiController

class JtiRouter(private val controller: JtiController ) {
    fun register() {
        ApiBuilder.post(
                "/jti",
            controller::validate
        )
    }
}