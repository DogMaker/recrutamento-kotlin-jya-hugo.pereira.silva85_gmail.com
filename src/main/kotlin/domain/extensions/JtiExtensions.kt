package domain.extensions

import java.time.Duration
import java.time.LocalDateTime


const val TWENTY_FOUR_HOURS_IN_MINUTES = 1440L

fun LocalDateTime?.isHigherThan24Hours(){

    fun toMinutes() = Duration.between(this, LocalDateTime.now()).toMinutes()

    toMinutes().takeIf{ it > TWENTY_FOUR_HOURS_IN_MINUTES }
        ?.let {
            throw Exception("The time is higher than 24 hours")
        }
}


    //LocalDateTime.now().minusHours(25).isHigherThan24Hours()
