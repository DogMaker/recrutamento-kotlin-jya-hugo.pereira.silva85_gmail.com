package domain.services

import domain.entities.Events
import repository.EventsRepository

class WebhookService(private val eventsRepository: EventsRepository) {
    fun saveWebhook(webhook: Events): Events {
        eventsRepository.insertEvent(webhook)
        return webhook
    }
}