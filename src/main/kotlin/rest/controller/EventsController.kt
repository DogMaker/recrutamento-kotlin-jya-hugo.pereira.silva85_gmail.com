package rest.controller

import domain.services.EventService
import io.javalin.http.Context
import org.eclipse.jetty.http.HttpStatus

class EventsController(private val eventsService: EventService){

    fun show(ctx: Context){
        val rate = try {
            eventsService.getEventList(ctx.pathParam("rate"))
        } catch (e: Exception) {
            println("stacktrace $e")
            throw Exception("Generic Exception of Events")
        }
        ctx.json("Show Events").status(HttpStatus.OK_200)
    }
}