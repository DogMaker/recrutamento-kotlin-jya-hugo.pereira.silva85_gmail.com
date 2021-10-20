package rest.configuration

import domain.services.JtiService
import org.koin.core.module.Module
import org.koin.dsl.module
import repository.JtiRepositoryImpl
import repository.config.PostgresDBConfig
import rest.controller.JtiController
import rest.routes.JtiRouter


val webhooksRoutesModule: Module = module {
    single { JtiRouter(get()) }
}


val jtiControllerModule: Module = module {
    single { JtiController(get()) }
}

val jtiServiceModule: Module = module {
    single { JtiService(JtiRepositoryImpl(PostgresDBConfig)) }
}

val postgresDBConfig: Module = module {
    single { PostgresDBConfig }
}

val jtiRepository: Module = module {
    single { JtiRepositoryImpl(PostgresDBConfig) }
}



