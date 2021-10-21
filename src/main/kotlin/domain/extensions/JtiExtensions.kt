package domain.extensions

import domain.entities.Jti
import domain.exceptions.DuplicateJtiException
import java.time.Duration
import java.time.LocalDateTime

const val TWENTY_FOUR_HOURS_IN_MINUTES = 1440L
const val FORTY_EIGHT_IN_MINUTES = 2880L

fun Jti.isExpired24Time(): Jti {

    println( "Jti expire time -> ${this.createdAt?.toLocalTime()}  | Time now -> ${LocalDateTime.now()?.toLocalTime()}")

    fun toMinutes() = Duration.between(this.createdAt, LocalDateTime.now()).toMinutes()

    toMinutes().takeIf{ it < TWENTY_FOUR_HOURS_IN_MINUTES }
        ?.let {
            throw DuplicateJtiException("The token already exists in the database and has not expired yet")
        }
    return this
}


