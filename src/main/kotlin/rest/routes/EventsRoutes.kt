package rest.routes

import io.javalin.apibuilder.ApiBuilder
import rest.controller.EventsController

class EventsRoutes(private val eventController: EventsController ) {
    fun register() {
        ApiBuilder.get(
                "/events/:numberArg",
                eventController::show
        )
    }
}