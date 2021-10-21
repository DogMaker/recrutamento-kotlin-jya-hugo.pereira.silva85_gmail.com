package rest.configuration

import domain.repository.JtiRepository
import domain.services.JtiService
import domain.services.impl.JtiServiceImpl
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

val jtiServiceImplModule: Module = module {
    single { JtiServiceImpl(JtiRepositoryImpl()) }
}

val jtiRepository: Module = module {
    single <JtiRepository>{ JtiRepositoryImpl() }
}



