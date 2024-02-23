package com.paranid5.star_wars_travel.data.di

import app.cash.sqldelight.db.SqlDriver
import com.paranid5.star_wars_travel.data.PlanetsRepository
import com.paranid5.star_wars_travel.data.ktor.KtorClient
import com.paranid5.star_wars_travel.data.ktor.PlanetsNetSource
import com.paranid5.star_wars_travel.data.ktor.PlanetsNetSourceImpl
import com.paranid5.star_wars_travel.data.sqldelight.PlanetsDbSource
import com.paranid5.star_wars_travel.data.sqldelight.PlanetsDbSourceImpl
import com.paranid5.star_wars_travel.data.sqldelight.SqlDelightClient
import io.ktor.client.HttpClient
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
    singleOf(::bindDbSource)
    singleOf(::bindNetSource)
    singleOf(::PlanetsRepository)
}

private fun bindDbSource(driver: SqlDriver): PlanetsDbSource =
    PlanetsDbSourceImpl(driver)

private fun bindNetSource(ktorClient: HttpClient): PlanetsNetSource =
    PlanetsNetSourceImpl(ktorClient)