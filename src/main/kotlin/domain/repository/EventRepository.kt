package domain.repository

import domain.entities.Events

interface EventRepository{
    fun showEvents(lastEvents: Int): List<Events>
}