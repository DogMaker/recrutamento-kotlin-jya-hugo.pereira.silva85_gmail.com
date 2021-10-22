package application.configuration

import com.natpryce.konfig.Configuration
import com.natpryce.konfig.EnvironmentVariables
import com.natpryce.konfig.getValue
import com.natpryce.konfig.intType
import com.natpryce.konfig.stringType

@SuppressWarnings("ObjectPropertyNaming")
class EnvironmentConfig(
    configuration: Configuration = EnvironmentVariables()
) {
    val serverPort = configuration[SERVER_PORT]
    val serviceSharedSecret = configuration[SERVICE_SHARED_SECRET]
    val databaseJdbcUrl = configuration[POSTGRES_JDBC_URL]
    val databaseUsername = configuration[POSTGRES_USER]
    val databasePassword = configuration[POSTGRES_PASS]
    val cronSchedulerTime = configuration[CRON_TIME]?: "0/5 * * * * ?"

    companion object {
        val SERVER_PORT by intType
        val SERVICE_SHARED_SECRET by stringType

        val POSTGRES_JDBC_URL by stringType
        val POSTGRES_USER by stringType
        val POSTGRES_PASS by stringType
        val CRON_TIME by stringType
    }
}