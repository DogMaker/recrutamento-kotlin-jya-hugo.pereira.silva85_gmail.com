package domain.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Events(
    val action: String?,
    val number: Int,
    val created_at:  String?,
    val commits: List<Commit>,
    val pusherName: String
)