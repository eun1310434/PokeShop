package com.euntaek.pokeshop.core.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val pokeShopDispatcher: PokeShopDispatchers)

enum class PokeShopDispatchers {
    Default,
    IO,
}
