import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import domain.entities.Jti
import io.restassured.RestAssured
import io.restassured.http.ContentType
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

fun post(
    movie: Jti,
    expectedStatus: Int
) = RestAssured.given()
    .contentType(ContentType.JSON)
    .body(objectMapper.writeValueAsBytes(movie)).post("/jti").then()
    .statusCode(expectedStatus)
    .extract()
    .jsonPath()

private val objectMapper = jacksonObjectMapper()
    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    .configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true)
    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
    .setSerializationInclusion(JsonInclude.Include.NON_NULL)
    .registerModule(JavaTimeModule()).setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE)
    .setDateFormat(SimpleDateFormat("yyyy-MM-ss"))

fun jtiSample() =   Jti(
    UUID.randomUUID().toString(),
     "C6Bank",
    LocalDateTime.now()
)