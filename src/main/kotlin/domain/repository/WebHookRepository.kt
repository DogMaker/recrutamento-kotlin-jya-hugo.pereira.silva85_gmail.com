package domain.repository

import domain.entities.Events

interface WebHookRepository{
    fun insertWebHook(webHook: Events):Boolean
}