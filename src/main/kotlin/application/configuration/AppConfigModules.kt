package application.configuration

import domain.repository.JtiRepository
import domain.services.impl.JtiServiceImpl
import org.koin.core.module.Module
import org.koin.dsl.module
import repository.JtiRepositoryImpl
import application.rest.controller.JtiController
import application.rest.routes.JtiRouter
import commons.DataBaseConfig


val jtiRouter: Module = module {
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


val environmentConfig: Module = module {
    single { EnvironmentConfig(get()) }
}

val dataBaseConfig: Module = module {
    single { DataBaseConfig(get()) }
}



