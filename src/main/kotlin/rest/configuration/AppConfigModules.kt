package rest.configuration

import domain.services.EventService
import domain.services.WebhookService
import org.koin.core.module.Module
import org.koin.dsl.module
import repository.EventsRepository
import repository.config.PostgresDBConfig
import rest.controller.EventsController
import rest.controller.WebhookController
import rest.routes.EventsRoutes
import rest.routes.WebhookRoutes


val eventsRoutesModule: Module = module {
    single { EventsRoutes(get()) }
}

val webhooksRoutesModule: Module = module {
    single { WebhookRoutes(get()) }
}

val eventsControllerModule: Module = module {
    single { EventsController(get()) }
}

val webhookControllerModule: Module = module {
    single { WebhookController(get()) }
}
val eventServiceModule: Module = module {
    single { EventService(EventsRepository(PostgresDBConfig)) }
}
val webhookServiceModule: Module = module {
    single { WebhookService(EventsRepository(PostgresDBConfig)) }
}

val postgresDBConfig: Module = module {
    single { PostgresDBConfig }
}

val eventsRepository: Module = module {
    single { EventsRepository(PostgresDBConfig) }
}



