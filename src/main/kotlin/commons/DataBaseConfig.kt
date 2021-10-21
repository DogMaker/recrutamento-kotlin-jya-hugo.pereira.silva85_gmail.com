package commons

import application.configuration.EnvironmentConfig
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database

class DataBaseConfig (
    private val environmentConfig: EnvironmentConfig
) {
    fun connect(): HikariDataSource {
        val config = HikariConfig()

        config.jdbcUrl = environmentConfig.databaseJdbcUrl
        config.username = environmentConfig.databaseUsername
        config.password = environmentConfig.databasePassword

        val dataSource = HikariDataSource(config)
        Database.connect(dataSource)

        migrate()

        return dataSource
    }

    private fun migrate() {
        Flyway.configure().dataSource(
            environmentConfig.databaseJdbcUrl,
            environmentConfig.databaseUsername,
            environmentConfig.databasePassword
        ).load().migrate()
    }
}