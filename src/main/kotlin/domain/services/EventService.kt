package domain.services

import domain.entities.Events
import repository.EventsRepository

class EventService(private val eventsRepository: EventsRepository){
    fun getEventList(pathParam: String) = eventsRepository.showEvents(pathParam.toInt())
}