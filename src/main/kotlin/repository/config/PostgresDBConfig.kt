package repository.config

import org.sql2o.Sql2o
import java.sql.DriverManager

private const val DB_URL = "jdbc:postgresql://localhost:5432/gitevent"
private const val USERNAME = "postgres"
private const val PASSWORD = "admin"


object PostgresDBConfig {
    fun startConnection() = Sql2o(DB_URL, USERNAME, PASSWORD).open().use { it }
    fun prepareDb() {
        val conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)
        conn.createStatement().use { stmt ->
            with(stmt) {
                executeUpdate("create table mytbl(id int primary key, name varchar(255))")
            }
        }
    }
}
