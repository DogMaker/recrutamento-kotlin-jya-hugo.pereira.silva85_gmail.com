package repository

import domain.entities.Events
import domain.repository.EventRepository
import repository.config.PostgresDBConfig
import java.lang.Exception

class EventsRepository(private val conn: PostgresDBConfig) : EventRepository {

    override fun showEvents(lastEvents: Int): List<Events> {

        val sql = """select * from Events"""
        return conn.startConnection().createQuery(sql).executeAndFetch(Events::class.java)
    }

    fun insertEvent(event: Events): Boolean {
        try {
            //conn.prepareDb()
            conn.startConnection().use { con -> con.createQuery("""insert into mytbl values(:id, :name)""")
                    .addParameter("id", event.number)
                    .addParameter("name", event.pusherName)
                    .executeUpdate() }
            return true
        } catch (e: Exception) {
            println(">>>>>>>>>>>>>>$e")
            throw Exception("Generic Excecption")
        }
    }

}

