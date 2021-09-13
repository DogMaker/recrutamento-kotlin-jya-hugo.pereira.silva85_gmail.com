package rest.controller

import domain.entities.Events
import domain.services.WebhookService
import io.javalin.http.Context
import org.eclipse.jetty.http.HttpStatus

class WebhookController(private val webhookService: WebhookService){

    fun save(ctx: Context){
        println(ctx)

        val webhook = try {
            ctx.body<Events>()
        } catch (e: Exception) {
            println("stacktrace $e")
            throw Exception("Generic Exception of Webhook")
        }
        val persisted = webhookService.saveWebhook(webhook)
        ctx.json(persisted).status(HttpStatus.OK_200)
    }
}
