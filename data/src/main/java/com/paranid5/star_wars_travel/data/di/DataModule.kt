package com.paranid5.star_wars_travel.data.di

import com.paranid5.star_wars_travel.data.PlanetsRepository
import com.paranid5.star_wars_travel.data.ktor.KtorClient
import com.paranid5.star_wars_travel.data.sqldelight.SqlDelightClient
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

private val ktorModule = module {
    singleOf(::KtorClient)
}

private val sqlDelightModule = module {
    single { SqlDelightClient(context = androidApplication()) }
}

val dataModule = module {
    includes(ktorModule, sqlDelightModule)
    singleOf(::PlanetsRepository)
}