package rest.controller

import domain.entities.Jti
import domain.services.JtiService
import io.javalin.http.Context
import org.eclipse.jetty.http.HttpStatus

class JtiController(private val service: JtiService){

    fun validate(ctx: Context){
        val jti = try {
            ctx.body<Jti>()
        } catch (e: Exception) {
            throw Exception("Generic Exception of Webhook")
        }
        val persisted = service.validate(jti)
        ctx.json(persisted).status(HttpStatus.CREATED_201)
    }
}
