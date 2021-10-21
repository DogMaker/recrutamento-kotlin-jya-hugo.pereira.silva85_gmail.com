package application.rest.controller

import application.rest.exceptions.DeserializationException
import domain.entities.Jti
import domain.services.impl.JtiServiceImpl
import io.javalin.http.Context
import org.eclipse.jetty.http.HttpStatus

class JtiController(private val service: JtiServiceImpl){

    fun validate(ctx: Context){
        val jti = try {
            ctx.body<Jti>()
        } catch (e: Exception) {
            throw DeserializationException("There was a problem to deserialize object")
        }
        val persisted = service.isAlreadyRegistered(jti)
        ctx.json(persisted).status(HttpStatus.CREATED_201)
    }
}