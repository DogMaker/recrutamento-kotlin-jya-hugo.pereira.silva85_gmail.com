package rest.routes

import io.javalin.apibuilder.ApiBuilder
import rest.controller.WebhookController

class WebhookRoutes(private val webhookController: WebhookController ) {
    fun register() {
        ApiBuilder.post(
                "/webhook",
                webhookController::save
        )
    }
}