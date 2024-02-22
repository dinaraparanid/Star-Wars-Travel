package com.paranid5.star_wars_travel.data.di

import com.paranid5.star_wars_travel.data.ktor.KtorClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

private val ktorModule = module {
    singleOf(::KtorClient)
}

val dataModule = module {
    includes(ktorModule)
}