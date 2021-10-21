package domain.extensions

import domain.entities.Jti
import domain.exceptions.DuplicateJtiException
import java.time.Duration
import java.time.LocalDateTime


const val TWENTY_FOUR_HOURS_IN_MINUTES = 1440L
const val FOURTY_EIGHT_IN_MINUTES = 2880L

fun Jti.isExpired24Time(): Jti {

    fun toMinutes() = Duration.between(this.createdAt, LocalDateTime.now()).toMinutes()

    toMinutes().takeIf{ it < TWENTY_FOUR_HOURS_IN_MINUTES }
        ?.let {
            throw DuplicateJtiException("The time of Jti is higher than 24 hours")
        }
    return this
}

fun LocalDateTime?.deleteExpiredJti(){

}


    //LocalDateTime.now().minusHours(25).isHigherThan24Hours()
