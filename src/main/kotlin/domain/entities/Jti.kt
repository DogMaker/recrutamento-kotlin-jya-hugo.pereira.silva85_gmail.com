package domain.entities

import java.time.LocalDateTime

data class Jti(
    val data: String,
    val clientId: String,
    val createdAt: LocalDateTime? = LocalDateTime.now()
)